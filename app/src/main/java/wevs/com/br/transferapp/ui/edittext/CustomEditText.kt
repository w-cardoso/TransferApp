package wevs.com.br.transferapp.ui.edittext

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import wevs.com.br.transferapp.R


class CustomEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val editText: EditText by lazy { findViewById<EditText>(R.id.item_edt) }
    private val textError by lazy { findViewById<TextView>(R.id.item_error_text) }
    private val viewError by lazy { findViewById<View>(R.id.item_error_view) }


    init {
        LayoutInflater.from(context).inflate(R.layout.custom_edit_text, this, true)


        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.custom_component_attributes, 0, 0
        )

        editText.hint = typedArray.getString(R.styleable.custom_component_attributes_textHint)
        typedArray.recycle()
    }

    fun hideError() {
        textError.visibility = View.GONE
        viewError.visibility = View.GONE
    }

    fun showError() {
        textError.visibility = View.VISIBLE
        viewError.visibility = View.VISIBLE
    }

}

