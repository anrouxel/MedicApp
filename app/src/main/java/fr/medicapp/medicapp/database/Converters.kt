package fr.medicapp.medicapp.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Classe Converters pour la conversion des types de données.
 */
class Converters {

    /**
     * Convertit une chaîne de caractères en LocalDate.
     *
     * @param localDate La date sous forme de chaîne de caractères.
     * @return La date sous forme de LocalDate.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDate(localDate: String): LocalDate {
        return LocalDate.parse(localDate)
    }

    /**
     * Convertit une LocalDate en chaîne de caractères.
     *
     * @param localDate La date sous forme de LocalDate.
     * @return La date sous forme de chaîne de caractères.
     */
    @TypeConverter
    fun toLocalDate(localDate: LocalDate): String {
        return localDate.toString()
    }

    /**
     * Convertit une chaîne de caractères en MutableList<String>.
     *
     * @param list La liste sous forme de chaîne de caractères.
     * @return La liste sous forme de MutableList<String>.
     */
    @TypeConverter
    fun toMutableList(list: String): MutableList<String> {
        return toList(list).toMutableList()
    }

    /**
     * Convertit une chaîne de caractères en une liste de chaînes de caractères.
     *
     * @param list La liste sous forme de chaîne de caractères.
     * @return La liste sous forme de List<String>.
     */
    @TypeConverter
    fun toList(list: String): List<String> {
        val cleanedList = list.substring(1 until list.length - 1)
        return cleanedList.split(",").map { it.trim() }
    }

    /**
     * Convertit une MutableList<String> en une chaîne de caractères.
     *
     * @param list La liste sous forme de MutableList<String>.
     * @return La liste sous forme de chaîne de caractères.
     */
    @TypeConverter
    fun fromMutableList(list: MutableList<String>): String {
        return fromList(list)
    }

    /**
     * Convertit une List<String> en une chaîne de caractères.
     *
     * @param list La liste sous forme de List<String>.
     * @return La liste sous forme de chaîne de caractères.
     */
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.toString()
    }

    /**
     * Convertit une chaîne de caractères en une liste de DayOfWeek.
     *
     * @param list La liste sous forme de chaîne de caractères.
     * @return La liste sous forme de MutableList<DayOfWeek>.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toMutableListDayOfWeek(list: String): MutableList<DayOfWeek> {
        return toListDayOfWeek(list).toMutableList()
    }

    /**
     * Convertit une chaîne de caractères en une liste de DayOfWeek.
     *
     * @param list La liste sous forme de chaîne de caractères.
     * @return La liste sous forme de List<DayOfWeek>.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toListDayOfWeek(list: String): List<DayOfWeek> {
        val cleanedList = list.substring(1 until list.length - 1)
        return cleanedList.split(",").map { DayOfWeek.of(it.trim().toInt()) }
    }

    /**
     * Convertit une MutableList<DayOfWeek> en une chaîne de caractères.
     *
     * @param list La liste sous forme de MutableList<DayOfWeek>.
     * @return La liste sous forme de chaîne de caractères.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromMutableListDayOfWeek(list: MutableList<DayOfWeek>): String {
        return fromListDayOfWeek(list)
    }

    /**
     * Convertit une List<DayOfWeek> en une chaîne de caractères.
     *
     * @param list La liste sous forme de List<DayOfWeek>.
     * @return La liste sous forme de chaîne de caractères.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromListDayOfWeek(list: List<DayOfWeek>): String {
        return list.map { it.value }.toString()
    }

    /**
     * Convertit une chaîne de caractères en une liste d'entiers.
     *
     * @param list La liste sous forme de chaîne de caractères.
     * @return La liste sous forme de MutableList<Int>.
     */
    @TypeConverter
    fun toMutableListInt(list: String): MutableList<Int> {
        return toListInt(list).toMutableList()
    }

    /**
     * Convertit une chaîne de caractères en une liste d'entiers.
     *
     * @param list La liste sous forme de chaîne de caractères.
     * @return La liste sous forme de List<Int>.
     */
    @TypeConverter
    fun toListInt(list: String): List<Int> {
        val cleanedList = list.substring(1 until list.length - 1)
        return cleanedList.split(",").map { it.trim().toInt() }
    }

    /**
     * Convertit une MutableList<Int> en une chaîne de caractères.
     *
     * @param list La liste sous forme de MutableList<Int>.
     * @return La liste sous forme de chaîne de caractères.
     */
    @TypeConverter
    fun fromMutableListInt(list: MutableList<Int>): String {
        return fromListInt(list)
    }

    /**
     * Convertit une List<Int> en une chaîne de caractères.
     *
     * @param list La liste sous forme de List<Int>.
     * @return La liste sous forme de chaîne de caractères.
     */
    @TypeConverter
    fun fromListInt(list: List<Int>): String {
        return list.toString()
    }
}
