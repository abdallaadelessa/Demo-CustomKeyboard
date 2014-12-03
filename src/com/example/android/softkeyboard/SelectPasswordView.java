package com.example.android.softkeyboard;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class SelectPasswordView extends FrameLayout
{
	private KeyboardServiceInterface keyServiceI;
	private Button btn_ok, btn_cancel;

	public SelectPasswordView(Context context, String autoFillAppName,
			KeyboardServiceInterface keyServiceI)
	{
		super(context);
		init(context, autoFillAppName, keyServiceI);
	}

	// ----------------------------------------

	private void init(final Context cxt, String autoFillAppName,
			KeyboardServiceInterface keyServiceI2)
	{
		this.keyServiceI = keyServiceI2;
		FrameLayout customView = (FrameLayout) inflate(cxt,
				R.layout.view_floating_autofillpassword, this);

		btn_ok = (Button) customView.findViewById(R.id.autofill_btn_ok);

		btn_cancel = (Button) customView.findViewById(R.id.autofill_btn_cancel);
		
		btn_ok.setOnClickListener(new ButtonWatcher());
		btn_cancel.setOnClickListener(new ButtonWatcher());
	}

	class ButtonWatcher implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			if(btn_ok.getId() == v.getId())
			{
				if (keyServiceI != null)
				{
					keyServiceI.paste("Testing...");
				}
			}
			else
			{
				if (keyServiceI != null)
				{
					keyServiceI.switchBack();
				}
			}
		}
	}

	// ----------------------------------------

	public static interface KeyboardServiceInterface
	{
		public void paste(String text);

		public void switchBack();
	}
}
