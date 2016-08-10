package com.ondrejruttkay.architecturedemo.common.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

/**
 * Created by Onko on 08/09/2016.
 */
public class FontCache {

//    public static final String BOLD = "bold";
//    public static final String REGULAR = "regular";
    public static final String ICONS = "icons";

    private static final String FONT_DIR = "fonts";
    private static Map<String, Typeface> cache = new HashMap<>();
    private static Map<String, String> fontMapping = new HashMap<>();
    private static FontCache instance;
    private Context context;

    public static FontCache getInstance() {
        return instance;
    }

    public static void initialize(Context context) {
        FontCache.instance = new FontCache(context);

//        FontCache.instance.replaceFont("MONOSPACE", BOLD);
//        FontCache.instance.replaceFont("SANS_SERIF", REGULAR);
        FontCache.instance.replaceFont("SERIF", ICONS);
    }

    private FontCache(Context context) {
        this.context = context;
        AssetManager am = context.getResources().getAssets();
        String fileList[];
        try {
            fileList = am.list(FONT_DIR);
        } catch (IOException e) {
            Timber.e("Error loading fonts from assets/fonts.");
            return;
        }

        for (String filename : fileList) {
            String alias = filename.substring(0, filename.lastIndexOf('.'));
            fontMapping.put(alias, filename);
            fontMapping.put(alias.toLowerCase(), filename);
        }

//        addFont(REGULAR, "Regular.ttf");
//        addFont(BOLD, "Bold.ttf");
        addFont(ICONS, "fontawesome.ttf");
    }

    public void addFont(String name, String fontFilename) {
        fontMapping.put(name, fontFilename);
    }

    public Typeface get(String fontName) {
        String fontFilename = fontMapping.get(fontName);
        if (fontFilename == null) {
            Timber.e("Couldn't find font %s. Maybe you need to call addFont() first?", fontName);
            return null;
        }
        if (cache.containsKey(fontName)) {
            return cache.get(fontName);
        } else {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), FONT_DIR + "/" + fontFilename);
            cache.put(fontFilename, typeface);
            return typeface;
        }
    }

    public void replaceFont(String oldTypefaceName, String newTypefaceName) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap<>();
            newMap.put(oldTypefaceName, get(newTypefaceName));
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField(oldTypefaceName);
                staticField.setAccessible(true);
                staticField.set(null, get(newTypefaceName));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
