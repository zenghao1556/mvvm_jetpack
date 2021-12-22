package com.zh.frame.library_network.interceptor

import android.text.TextUtils
import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.GzipSource
import java.io.EOFException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.interceptor
 * @ClassName: LoggingInterceptor
 * @Description: 日志拦截器
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 6:06 下午
 */
class LoggingInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // chain中拿要拦截的信息
//        if (!logEnable) return chain.proceed(chain.request())

        // request
        val request = chain.request()
        val method = request.method
        val url = request.url
        Log.i("HttpLogging", "【Http】Request start")
        Log.i("HttpLogging", "【Http】$method $url")

        // connection
        val connection = chain.connection()
        val protocol = connection?.protocol()
        protocol?.let { Log.i("HttpLogging", "【Http】protocol:$it") }

        // headers
        val requestHeaders = request.headers
       /* requestHeaders.forEach {
            Log.i("HttpLogging", "【Http】[header]${it.first}:${it.second}")
        }
*/
        // body
        val reqBody = request.body
        reqBody?.contentType()?.let { Log.i("HttpLogging", "【Http】Content-Type:${it}") }
        reqBody?.contentLength()?.let { Log.i("HttpLogging", "【Http】Content-Length:${it}") }
        Log.i("HttpLogging", "【Http】Request $method end")




        Log.i("HttpLogging", "【Http】Response $method start")
        val startNs = System.nanoTime()
        // response
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            Log.i("HttpLogging", "【Http】Failed, e:${e.message}")
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        Log.i("HttpLogging", "【Http】code:${response.code} ($tookMs ms)")

        // headers
        val respHeaders = response.headers

        /*respHeaders.forEach {
            Log.i("HttpLogging", "【Http】[header]${it.first}:${it.second}")
        }*/

        // body
        val respBody = response.body
        response.message.let {
            if (!TextUtils.isEmpty(it)) Log.i("HttpLogging", "【Http】message:${it}")
        }

        val contentLength = respBody?.contentLength()
        val bodySize = if (contentLength != -1L) "$contentLength-byte" else "unknown-length"

        if (a(respHeaders)) {
            Log.i("HttpLogging", "【Http】Response $method end ($bodySize)")
        } else {
            val source = respBody?.source()
            source?.request(9223372036854775807L)
            var buffer = source?.buffer
            var gzippedLength = 0L
            if ("gzip".equals(respHeaders["Content-Encoding"], true)) {
                gzippedLength = buffer?.size!!
                var gzippedResponseBody: GzipSource? = null
                try {
                    gzippedResponseBody = GzipSource(buffer.clone())
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                } finally {
                    gzippedResponseBody?.close()
                }
            }
            val mediaType = respBody?.contentType()
            val charset = mediaType?.charset(Charset.forName("UTF-8"))

            if (buffer != null && !a(buffer)) {
                Log.i("HttpLogging", "【Http】Response $method end")
                return response
            }

            if (contentLength != 0L) {
                charset?.let {
                    Log.i("HttpLogging",
                        "【Http】msg:${buffer?.clone()?.readString(it)}")
                }
            }

            Log.i("HttpLogging", "【Http】Response $method end (${buffer?.size}-byte"
                    + if (gzippedLength != 0L) ", $gzippedLength--gzipped-byte)" else ")")
        }
        return response
    }

    private fun a(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"]
        return contentEncoding != null && !contentEncoding.equals("identity",
            true) && !contentEncoding.equals("gzip", true)
    }

    private fun a(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount = if (buffer.size < 64L) buffer.size else 64L
            buffer.copyTo(prefix, 0L, byteCount)
            var i = 0
            while (i < 16 && !prefix.exhausted()) {
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
                ++i
            }
            true
        } catch (var6: EOFException) {
            false
        }
    }
}