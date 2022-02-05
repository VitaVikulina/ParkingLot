package parking

import java.util.Scanner

data class Car(val code: String, val color: String) {
    override fun toString(): String = "$code $color"
}

class Parking(spots: Int) {
    val parking: MutableList<Car?> = MutableList(spots) { null }

    fun park(code: String, color: String) {
        if (null in parking) {
            val index = parking.indexOf(null)
            parking[index] = Car(code, color.lowercase())
            println("$color car parked in spot ${index + 1}.")
        } else {
            println("Sorry, the parking lot is full.")
        }
    }

    fun leave(spot: Int) {
        if (parking[spot - 1] != null) {
            parking[spot - 1] = null
            println("Spot $spot is free.")
        } else {
            println("There is no car in spot $spot.")
        }
    }

    fun status() {
        for (i in parking.indices) {
            if (parking[i] != null) println("$i ${parking[i]}")
        }
    }

    fun reg_by_color(color: String) {
        val list = mutableListOf<String>()

        for (i in parking) {
            if (i?.color == color.lowercase()) list.add(i?.code)
        }

        if (list.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            println(list.joinToString(", "))
        }
    }

    fun spot_by_color(color: String) {
        val list = mutableListOf<Int>()

        for (i in parking.indices) {
            if (parking[i]?.color == color.lowercase()) list.add(i + 1)
        }

        if (list.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            println(list.joinToString(", "))
        }
    }

    fun spot_by_reg(code: String) {
        for (i in parking.indices) {
            if (parking[i]?.code == code) {
                println(i + 1)
                return
            }
        }

        println("No cars with registration number $code were found.")
    }
}

fun create(spots: Int): Parking {
    println("Created a parking lot with $spots spots.")
    return Parking(spots)
}

fun main() {
    var parking: Parking? = null

    val scanner = Scanner(System.`in`)
    while(true) {
        try {
            when (scanner.next()) {
                "create" -> parking = create(scanner.nextInt())
                "park" -> parking!!.park(scanner.next(), scanner.next())
                "leave" -> parking!!.leave(scanner.nextInt())
                "status" -> parking!!.status()
                "reg_by_color" -> parking!!.reg_by_color(scanner.next())
                "spot_by_color" -> parking!!.spot_by_color(scanner.next())
                "spot_by_reg" -> parking!!.spot_by_reg(scanner.next())
                "exit" -> return
            }
        } catch (e: NullPointerException) {
            println("Sorry, a parking lot has not been created.")
        }
    }
}
