@file:JvmName("Lwjgl3Launcher")

package paradokso.kegma.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import paradokso.kegma.Kegma

/** Launches the desktop (LWJGL3) application. */
fun main() {
    Lwjgl3Application(Kegma(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("Kegma")
        setWindowedMode(1024, 768)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
