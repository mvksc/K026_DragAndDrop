package m.vk.k026_draganddrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drag_drop_button.tag = "Button"
        drag_drop_button.setOnTouchListener(DragDropOnTouchListener())

        drag_drop_image.tag = "ImageView"
        drag_drop_image.setOnTouchListener(DragDropOnTouchListener())

        drag_drop_top_layout.setOnDragListener(DragDropOnDragListener(applicationContext))
        drag_drop_bottom_layout.setOnDragListener(DragDropOnDragListener(applicationContext))
    }
}
