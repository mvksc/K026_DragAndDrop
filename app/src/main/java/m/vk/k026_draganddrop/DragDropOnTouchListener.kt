package m.vk.k026_draganddrop

import android.content.ClipData
import android.os.Build
import android.view.MotionEvent
import android.view.View

class DragDropOnTouchListener : View.OnTouchListener{
    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        // Get view object tag value.
        var tag = view?.tag as String

        // Create clip data.
        var clipData = ClipData.newPlainText("",tag)

        // Create drag shadow builder object.
        var dragShadowBuilder = View.DragShadowBuilder(view)

        /* Invoke view object's startDrag method to start the drag action.
           clipData : to be dragged data.
           dragShadowBuilder : the shadow of the dragged view.
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(clipData,dragShadowBuilder,view,0)
        }else{
            @Suppress("DEPRECATION")
            view.startDrag(clipData,dragShadowBuilder,view,0)
        }

        // Hide the view object because we are dragging it.
        view.visibility = View.INVISIBLE

        return true
    }
}