package com.anmaoff.walletview

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity : AppCompatActivity() {

    // Datos de ejemplo: categoría y monto del gasto mensual
    private val gastos = listOf(
        "Comida" to 3500f,
        "Transporte" to 1200f,
        "Renta" to 5000f,
        "Ocio" to 900f,
        "Servicios" to 1500f
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configurarPastel(findViewById(R.id.pieChart))
        configurarBarras(findViewById(R.id.barChart))
    }

    // Gráfica de pastel: porcentaje de cada categoría
    private fun configurarPastel(chart: PieChart) {
        val entradas = gastos.map { PieEntry(it.second, it.first) }

        val dataSet = PieDataSet(entradas, "").apply {
            colors = ColorTemplate.MATERIAL_COLORS.toList()
            valueTextSize = 14f
            valueTextColor = Color.WHITE
        }

        chart.apply {
            data = PieData(dataSet)
            description.isEnabled = false
            centerText = "Gastos"
            setUsePercentValues(false)
            animateY(1000) // animación al abrir
            invalidate()
        }
    }

    // Gráfica de barras: monto por categoría
    private fun configurarBarras(chart: BarChart) {
        val entradas = gastos.mapIndexed { i, g -> BarEntry(i.toFloat(), g.second) }

        val dataSet = BarDataSet(entradas, "Gasto mensual (MXN)").apply {
            colors = ColorTemplate.MATERIAL_COLORS.toList()
            valueTextSize = 12f
        }

        chart.apply {
            data = BarData(dataSet)
            description.isEnabled = false
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(gastos.map { it.first })
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                setDrawGridLines(false)
            }
            axisRight.isEnabled = false
            animateY(1000)
            invalidate()
        }
    }
}