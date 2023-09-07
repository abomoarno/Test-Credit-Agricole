package com.adaptive.testmobileca.utils

import android.content.Context
import okio.IOException

/**
 * Created by Arno ABOMO on 09/06/2023
 */
object AssetsUtils {

    /*
     * This method is used to read the json file containing mocked data from the assets folder
     */
    fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            // Append the json extension
            jsonString = context.assets.open("banks.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}