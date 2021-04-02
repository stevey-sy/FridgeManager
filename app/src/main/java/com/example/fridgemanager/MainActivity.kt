package com.example.fridgemanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.fridgemanager.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.integration.android.IntentIntegrator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        // view binding 처리
//        binding.button.setOnClickListener {
//            val integrator = IntentIntegrator(this)
//            integrator.initiateScan();
//        }
//
//        binding.addItemButton.setOnClickListener {
//            // 데이터를 저장할 수 있는 form 이 나와야됨.
//            // fragment
//        }
    }

//    private fun initNavigation() {
//        NavigationUI.setupWithNavController(binding.navi, findNavController(R.id.navi_host))
//    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

//    // Get the results:
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//        if (result != null) {
//            if (result.contents == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
//            } else {
//                // 바코드 표시
//                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
//                // api 서버에 바코드 조회 전송하는 메소드
//                // json 데이터 받아서 text view 에 뿌리는 것까지.
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data)
//        }
//    }

    override fun onResume() {
        super.onResume()
        // 초기 fragment 세팅
        supportFragmentManager.beginTransaction().replace(R.id.main_content, FridgeFragment())
            .commit()

        // Bottom Navigation View 등록
        transitonNavigationBottomView(binding.navi, supportFragmentManager)
    }

    // NavigationBottomView 화면 전환하는 함수.
    private fun transitonNavigationBottomView(bottomView: BottomNavigationView, fragmentManager: FragmentManager){
        bottomView.setOnNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.action_fridge -> {
                    fragmentManager.beginTransaction().replace(R.id.main_content, FridgeFragment()).commit()
                }
                R.id.action_recipe -> {
                    fragmentManager.beginTransaction().replace(R.id.main_content, RecipeFragment()).commit()
                }
                R.id.action_board -> {
                    fragmentManager.beginTransaction().replace(R.id.main_content, BoardFragment()).commit()
                }
                else -> Log.d("test", "error") == 0
            }
            Log.d("test", "final") == 0
        }
    }


}