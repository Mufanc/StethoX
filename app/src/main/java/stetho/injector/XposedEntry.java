package stetho.injector;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedEntry implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod(
            Application.class,
            "onCreate",
            new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    Stetho.initializeWithDefaults((Application) param.thisObject);
                    Log.i("StethoX", "Inject!");
                }
            }
        );
    }
}
