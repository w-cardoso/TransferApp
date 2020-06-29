package wevs.com.br.transferapp.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private fun prepare(func: LoginActivityPrepare.() -> Unit) =
        LoginActivityPrepare().apply { (func) }

    private fun execute(func: LoginActivityExecute.() -> Unit) =
        LoginActivityExecute().apply { (func) }

    private fun validate(func: LoginActivityValidate.() -> Unit) =
        LoginActivityValidate().apply { (func) }
}