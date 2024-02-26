package fr.medicapp.medicapp.database.converter

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class LocalDateTypeAdapter :
    JsonSerializer<LocalDate?>,
    JsonDeserializer<LocalDate?> {
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun serialize(
        src: LocalDate?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonPrimitive {
        return JsonPrimitive(formatter.format(src))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate {
        return LocalDate.parse(json.asString, formatter)
    }
}