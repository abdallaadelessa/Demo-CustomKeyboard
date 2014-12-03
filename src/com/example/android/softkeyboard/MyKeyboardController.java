package com.example.android.softkeyboard;

import java.util.List;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

public class MyKeyboardController
{
	private static final Class<?> MYKEYBOARD_CLASS = SoftKeyboard.class;

	public static InputMethodInfo getMyCustomKeyboard(Context cxt)
	{
		InputMethodInfo imf = null;

		InputMethodManager inputManager = (InputMethodManager) cxt
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		List<InputMethodInfo> inputMethodList = inputManager
				.getEnabledInputMethodList();
		for (InputMethodInfo m : inputMethodList)
		{
			if (m.getServiceName().equalsIgnoreCase(
					MYKEYBOARD_CLASS.getName()))
			{
				imf = m;
				break;
			}
		}

		return imf;
	}
	
	public static boolean isMyInputMethodEnabled(Context cxt)
	{
		String id = Settings.Secure.getString(cxt.getContentResolver(),
				Settings.Secure.DEFAULT_INPUT_METHOD);

		ComponentName defaultInputMethod = ComponentName
				.unflattenFromString(id);

		ComponentName myInputMethod = new ComponentName(cxt,
				MYKEYBOARD_CLASS);

		return myInputMethod.equals(defaultInputMethod);
	}

	public static String getAppNameByPackage(Context context, String packageName)
	{
		CharSequence result = null;
		try
		{
			if (packageName != null)
			{
				ApplicationInfo applicationInfo = context.getPackageManager()
						.getApplicationInfo(packageName, 0);
				result = context.getPackageManager().getApplicationLabel(
						applicationInfo);
			}
		}
		catch (NameNotFoundException e)
		{

		}

		return result == null ? null : result.toString();
	}
}
