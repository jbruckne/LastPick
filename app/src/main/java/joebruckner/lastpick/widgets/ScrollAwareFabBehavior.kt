package joebruckner.lastpick.widgets

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.view.View

class ScrollAwareFabBehavior: CoordinatorLayout.Behavior<FloatingActionButton> {

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?,
                                     child: FloatingActionButton?, directTargetChild: View?,
                                     target: View?, nestedScrollAxes: Int): Boolean {
        return true
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout?,
                                child: FloatingActionButton?, target: View?, dxConsumed: Int,
                                dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && child?.visibility == View.VISIBLE) {
            child?.hide()
        } else if (dyConsumed < 0 && child?.visibility != View.VISIBLE) {
            child?.show()
        }
    }
}