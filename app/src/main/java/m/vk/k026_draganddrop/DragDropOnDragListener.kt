package m.vk.k026_draganddrop

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import android.graphics.PorterDuff
import kotlin.math.roundToInt

class DragDropOnDragListener(var context: Context) : View.OnDragListener{

    override fun onDrag(view: View?, dragEvent: DragEvent?): Boolean {
        // Get the drag drop action.
        var dragAction = dragEvent?.action


        if(dragAction == DragEvent.ACTION_DRAG_STARTED){
            // Check whether the dragged view can be placed in this target view or not.
            var clipDescription = dragEvent?.clipDescription
            if (clipDescription?.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)!!){
                // Return true because the target view can accept the dragged object.
                return true
            }
        }else if (dragAction == DragEvent.ACTION_DRAG_ENTERED){
              // When the being dragged view enter the target view, change the target view background color.
            changeTargetViewBackground(view!!, Color.GREEN)
            return true
        }else if (dragAction == DragEvent.ACTION_DRAG_EXITED){
            // When the being dragged view exit target view area, clear the background color.
            resetTargetViewBackground(view!!)
            return true
        }else if (dragAction == DragEvent.ACTION_DRAG_ENDED){
            // When the drop ended reset target view background color.
            resetTargetViewBackground(view!!)

            // Get drag and drop action result.
            var result = dragEvent?.result as Boolean

            // result is true means drag and drop action success.
            if (result){
                //Toast.makeText(context,"Drag and drop action complete successfully.",Toast.LENGTH_SHORT).show()
            }else{
                //Toast.makeText(context,"Drag and drop action failed.",Toast.LENGTH_SHORT).show()
            }
            return true
        }else if (dragAction == DragEvent.ACTION_DROP){// When drop action happened.
            // Get clip data in the drag event first.
            var clipData = dragEvent?.clipData as ClipData

            // Get drag and drop item count.
            var itemCount = clipData.itemCount

            // If item count bigger than 0.
            if (itemCount > 0){
                // Get clipdata item.
                var item = clipData.getItemAt(0)

                // Get item text.
                var dragDropString = item.text.toString()

                // Show a toast popup.
                //Toast.makeText(context,"Dragged object is a $dragDropString .",Toast.LENGTH_SHORT).show()

                // Reset target view background color.
                resetTargetViewBackground(view!!)

                // Get dragged view object from drag event object.
                val srcView = dragEvent.localState as View

                // Get dragged view's parent view group.
                val owner = srcView.parent as ViewGroup
                // Remove source view from original parent view group.
                owner.removeView(srcView)

                // The drop target view is a LinearLayout object so cast the view object to it's type.
                val newContainer = view as LinearLayout
                // Add the dragged view in the new container.
                newContainer.addView(srcView)
                // Make the dragged view object visible.
                srcView.visibility = View.VISIBLE

                // Returns true to make DragEvent.getResult() value to true.
                return true
            }
        }else if (dragAction == DragEvent.ACTION_DRAG_LOCATION){
            return true
        }else{
            Toast.makeText(context,"Drag and drop un know action type.",Toast.LENGTH_SHORT).show()
        }

        return false
    }

    /* Reset drag and drop target view's background color. */
    private fun resetTargetViewBackground(view: View){
        view.background.clearColorFilter()// Clear color filter.
        view.invalidate()// Redraw the target view use original color.
    }

    /* Change drag and drop target view's background color. */
    private fun changeTargetViewBackground(view: View, color: Int) {
        /* When the being dragged view enter the target view, change the target view background color.
        *
        *  color : The changed to color value.
        *
        *  mode : When to change the color, SRC_IN means source view ( dragged view ) enter target view.
        * */
        view.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)

        // Redraw the target view use new color.
        view.invalidate()
    }
}