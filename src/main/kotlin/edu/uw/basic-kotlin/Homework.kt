package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    when (arg) {
        is String -> {
            when (arg) {
                "Hello" -> return "world"
                else -> return "Say what?"
            }
        }
        is Int -> {
            when (arg) {
                0 -> return "zero"
                1 -> return "one"
                in 2..10 -> return "low number"
                else -> return "a number"
            }
        }
        else -> return "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an
// Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person(var firstName: String, var lastName: String, var age: Int) {
    val debugString: String
        get() = "[Person firstName:${this.firstName} lastName:${this.lastName} age:${this.age}]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Ammount cannot be negative")
        }
        if (currency !in listOf("USD", "GBP", "CAN", "EUR")) {
            throw IllegalArgumentException("Invalid currency")
        }
    }

    fun convert(otherCurr: String): Money {
        if (otherCurr !in listOf("USD", "GBP", "CAN", "EUR")) {
            throw IllegalArgumentException("Invalid currency")
        }

        if (this.currency == otherCurr) {
            return Money(this.amount, otherCurr)
        } else {
            when (Pair(currency, otherCurr)) {
                Pair("USD", "GBP") -> return Money((this.amount * 0.5).toInt(), "GBP")
                Pair("USD", "EUR") -> return Money((this.amount * 1.5).toInt(), "EUR")
                Pair("USD", "CAN") -> return Money((this.amount * 1.25).toInt(), "CAN")
                Pair("GBP", "USD") -> return Money((this.amount * 2).toInt(), "USD")
                Pair("EUR", "USD") -> return Money((this.amount * .75).toInt(), "USD")
                Pair("CAN", "USD") -> return Money((this.amount * 5 / 4).toInt(), "USD")
                else -> return convert("USD").convert(otherCurr)
            }
        }
    }

    operator fun plus(other: Money): Money {
        return Money(this.amount + (other.convert(this.currency)).amount, this.currency)
    }
}
