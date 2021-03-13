package com.example.binding.src.main.menu.btm_sheet

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.binding.R
import com.example.binding.databinding.LayoutBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class BottomSheetLayout(): BottomSheetDialogFragment() {
    private var _binding: LayoutBottomSheetBinding? = null
    private val binding get() = _binding!!

    lateinit var bigLocationList: ArrayList<String> // 첫번째 지역(시/도) 리스트뷰 데이터
    lateinit var bigAdapter: ArrayAdapter<String>   // 첫번째 지역 리스트뷰 어댑터
    lateinit var smallLocationList: ArrayList<String> // 두번째 지역(구) 리스트뷰 데이터
    lateinit var smallAdapter: ArrayAdapter<String>   // 두번째 지역 리스트뷰 어댑터

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val btmSheetDialog =  super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        // 커스텀 다이얼로그 뷰 생성,적용
        val view = View.inflate(context, R.layout.layout_bottom_sheet, null)
        btmSheetDialog.setContentView(view)

        // 바텀시트 크기 설정
        val btmSheetContainer = btmSheetDialog.findViewById<View>(R.id.design_bottom_sheet)
        val params = btmSheetContainer?.layoutParams
        val screenHeight = activity!!.resources.displayMetrics.heightPixels
        params?.height = (screenHeight * 0.66).toInt()
        // params?.height = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        btmSheetContainer?.layoutParams = params

        return btmSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 첫번째 지역 선택 리스트뷰 설정
        bigLocationList = arrayListOf("서울", "경기도", "인천")
        bigAdapter = ArrayAdapter(context!!, R.layout.item_big_location,bigLocationList)
        binding.bottomSheetBigList.adapter = bigAdapter
        binding.bottomSheetBigList.onItemClickListener = onBigClick

        // 두번째 지역 선택 리스트뷰 설정
        smallLocationList = arrayListOf()
        smallLocationList.addAll(resources.getStringArray(R.array.Seoul))
        smallAdapter = ArrayAdapter(context!!, R.layout.item_small_location, smallLocationList)
        binding.bottomSheetSmallList.adapter = smallAdapter
        binding.bottomSheetSmallList.onItemClickListener = onSmallClick

    }

    private val onBigClick = AdapterView.OnItemClickListener { parent, view, position, id ->
        val location = (view as TextView).text.toString()
        Log.d("로그", "location1: $location")

        // 선택한 지역에 맞게 2번째 리스트뷰 업데이트
        when(location){
            "서울" -> smallLocationList.let{
                it.clear()
                it.addAll(resources.getStringArray(R.array.Seoul))
            }
            "경기도" ->
                smallLocationList.let{
                    it.clear()
                    it.addAll(resources.getStringArray(R.array.Gyeonggi))
            }
            "인천" -> smallLocationList.let{
                it.clear()
                it.addAll(resources.getStringArray(R.array.Incheon))
            }
        }
        smallAdapter.notifyDataSetChanged()
    }

    private val onSmallClick = AdapterView.OnItemClickListener { parent, view, position, id ->
        val location = (view as TextView).text.toString()
        Log.d("로그", "location2: $location")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutBottomSheetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showCustomToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}