package joebruckner.lastpick.ui.common

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import joebruckner.lastpick.R

abstract class BaseFragment : Fragment() {
    open val menuId: Int = R.menu.menu_empty
    abstract val layoutId: Int
    lateinit var parent: BaseActivity
    protected var baseView: View? = null
    protected var isFirstStart = true

    val logTag = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parent = context as BaseActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        baseView = view
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(menuId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        isFirstStart = false
    }
}