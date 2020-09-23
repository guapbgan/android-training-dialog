package com.uuu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class ClickAction implements OnClickListener {

	private Context m_context;
	
	public ClickAction(Context mContext) {
		super();
		m_context = mContext;
	}

	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		String message = String.format("#%1$d is clicked", which);
		Toast.makeText(m_context,message , Toast.LENGTH_LONG).show();
		dialog.cancel();
		ProgressDialog.show(m_context, "this is the title", "message", true, true);

	}

}
