package paradokso.kegma.components

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.github.quillraven.fleks.ComponentListener
import com.github.quillraven.fleks.Entity

class ImageComponent : Comparable<ImageComponent> {
    lateinit var image: Image

    override fun compareTo(other: ImageComponent): Int {
        val yDiff = image.y.compareTo(other.image.y)
        return if (yDiff == 0) {
            image.x.compareTo(other.image.x)
        } else {
            yDiff
        }
    }

    companion object {
        class ImageComponentListener(
            private val stage: Stage,
        ) : ComponentListener<ImageComponent> {

            override fun onComponentAdded(entity: Entity, component: ImageComponent) {
                stage.addActor(component.image)
            }

            override fun onComponentRemoved(entity: Entity, component: ImageComponent) {
                stage.root.removeActor(component.image)
            }

        }
    }

}