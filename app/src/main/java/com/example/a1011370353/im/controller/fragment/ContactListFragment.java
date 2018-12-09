package com.example.a1011370353.im.controller.fragment;

import android.content.Intent;
import android.view.View;

import com.example.a1011370353.im.R;
import com.example.a1011370353.im.controller.activity.AddContactActivity;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * Created by 1011370353 on 2018/12/8.
 */

//联系人列表页面
public class ContactListFragment extends EaseContactListFragment {
    @Override
    protected void initView() {
        super.initView();
        //布局显示加号
        titleBar.setRightImageResource(R.drawable.em_add);
        //添加头布局
        View headView = View.inflate(getActivity(), R.layout.header_fragment_contact, null);

        listView.addHeaderView(headView);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        //添加按钮的点击事件处理
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddContactActivity.class);
                startActivity(intent);

            }
        });
    }
}
