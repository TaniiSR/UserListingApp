package com.task.userapp.data.dtos

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelsParsingTest {

    @Test
    fun `test json response maps to user model`() {
        val userList = "MockUserListResponse.json".readJsonUserFile()
        assertEquals(10, userList.size)
        assertEquals("User 1", userList.first().name)
        assertEquals("User 10", userList.last().name)
    }

    @Test
    fun `test json response maps to post model`() {
        val postList = "MockPostListResponse.json".readJsonPostFile()
        assertEquals(29, postList.size)
        assertEquals(1, postList.first().id)
        assertEquals(35, postList.last().id)
    }

    private fun String.readJsonPostFile(): PostResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<PostResponse>() {}.type
        return gson.fromJson(readFileFromTestResources(this), itemType)
    }

    private fun String.readJsonUserFile(): UserResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<UserResponse>() {}.type
        return gson.fromJson(readFileFromTestResources(this), itemType)
    }

    @Throws(IOException::class)
    fun readFileFromTestResources(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream = getInputStreamFromResource(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            return builder.toString()
        } finally {
            inputStream?.close()
        }
    }

    private fun getInputStreamFromResource(fileName: String) =
        javaClass.classLoader?.getResourceAsStream(fileName)
}