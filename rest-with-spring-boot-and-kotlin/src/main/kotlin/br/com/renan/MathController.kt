package br.com.renan


import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {
    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{numberOne}/{NumberTwo}"])
    fun sum(
        @PathVariable(value = "NumberOne") numberOne: String?,
        @PathVariable(value = "NumberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw Exception("Valor é invalido")
        } else {
            return convertToDouble(numberOne) + convertToDouble(numberTwo)
        }
    }
    private fun convertToDouble(strNumber: String?): Double {
        if (strNumber.isNullOrBlank()) return 0.0
        val number = strNumber.replace(",".toRegex(), ".")
        return if (isNumeric(number)) number.toDouble() else 0.0
    }


    private fun isNumeric(strNumber: String?): Boolean {
        if (strNumber.isNullOrBlank()) return false;
        val number = strNumber.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}





















