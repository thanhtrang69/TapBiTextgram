package com.example.trang.tapbitextgram.untils;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MSI on 2/20/2017.
 */

public class FontProvider {

    private static final String DEFAULT_FONT_NAME = "AnsleyDisplay-Bold";

    private final Map<String, Typeface> typefaces;
    private final Map<String, String> fontNameToTypefaceFile;
    private final Resources resources;
    private final List<String> fontNames;
    public FontProvider(Resources resources) {
        this.resources = resources;

        typefaces = new HashMap<>();

        // populate fonts
        fontNameToTypefaceFile = new HashMap<>();
        fontNameToTypefaceFile.put("AnsleyDisplay-Bold", "AnsleyDisplay-Bold.ttf");
        fontNameToTypefaceFile.put("Archive", "Archive.otf");
        fontNameToTypefaceFile.put("BebasNeueBold", "BebasNeueBold.otf");
        fontNameToTypefaceFile.put("BERNIERShade-Regular", "BERNIERShade-Regular.otf");
        fontNameToTypefaceFile.put("bigjohn", "bigjohn.otf");
        fontNameToTypefaceFile.put("Borg", "Borg.ttf");
        fontNameToTypefaceFile.put("Cornerstone", "Cornerstone.ttf");
        fontNameToTypefaceFile.put("DroidSerif", "DroidSerif.ttf");
        fontNameToTypefaceFile.put("fabfeltscript-bold", "fabfeltscript-bold.ttf");
        fontNameToTypefaceFile.put("Handwriting", "Handwriting.ttf");
        fontNameToTypefaceFile.put("Humblle", "Humblle.otf");
        fontNameToTypefaceFile.put("Lora-Italic", "Lora-Italic.ttf");
        fontNameToTypefaceFile.put("Lovelo-Line-Bold", "Lovelo-Line-Bold.otf");
        fontNameToTypefaceFile.put("lumberjack", "lumberjack.ttf");
        fontNameToTypefaceFile.put("manteka", "manteka.ttf");
        fontNameToTypefaceFile.put("Modeka", "Modeka.otf");
        fontNameToTypefaceFile.put("Moonshiner", "Moonshiner.otf");
        fontNameToTypefaceFile.put("Orkney", "Orkney.otf");
        fontNameToTypefaceFile.put("PlayfairDisplay", "PlayfairDisplay.otf");
        fontNameToTypefaceFile.put("plstk", "plstk.ttf");
        fontNameToTypefaceFile.put("Raleway", "Raleway.ttf");
        fontNameToTypefaceFile.put("RobotoSlab", "RobotoSlab.ttf");
        fontNameToTypefaceFile.put("Shumi", "Shumi.otf");
        fontNameToTypefaceFile.put("slimjoe", "slimjoe.otf");
        fontNameToTypefaceFile.put("Swagger", "Swagger.ttf");
        fontNameToTypefaceFile.put("TrueLove", "TrueLove.ttf");
        fontNameToTypefaceFile.put("TrueLove-bold", "TrueLove-bold.ttf");
        fontNameToTypefaceFile.put("zzAraAsmaaBeltajie-Regular", "zzAraAsmaaBeltajie-Regular.otf");
        fontNameToTypefaceFile.put("zzAraESTaqniya-Bold", "zzAraESTaqniya-Bold.otf");
        fontNameToTypefaceFile.put("zzAraEtabAlMonieee-Regular", "zzAraEtabAlMonieee-Regular.otf");
        fontNameToTypefaceFile.put("zzAraHamah1964B-Bold", "zzAraHamah1964B-Bold.ttf");
        fontNameToTypefaceFile.put("zzAraHamahAlFidaa-Regular", "zzAraHamahAlFidaa-Regular.ttf");
        fontNameToTypefaceFile.put("zzAraHamahAlislam-Regular", "zzAraHamahAlislam-Regular.ttf");
        fontNameToTypefaceFile.put("zzAraOsamaSubtitle-Regular", "zzAraOsamaSubtitle-Regular.otf");

        fontNames = new ArrayList<>(fontNameToTypefaceFile.keySet());
    }

    /**
     * @param typefaceName must be one of the font names provided from {@link FontProvider#getFontNames()}
     * @return the Typeface associated with {@code typefaceName}, or {@link Typeface#DEFAULT} otherwise
     */
    public Typeface getTypeface(@Nullable String typefaceName) { 
        if (TextUtils.isEmpty(typefaceName)) {
            return Typeface.DEFAULT;
        } else {
            //noinspection Java8CollectionsApi
            if (typefaces.get(typefaceName) == null) {
                typefaces.put(typefaceName,
                        Typeface.createFromAsset(resources.getAssets(), "fonts/" + fontNameToTypefaceFile.get(typefaceName)));
            }
            return typefaces.get(typefaceName);
        }
    }

    /**
     * use {@link FontProvider#getTypeface(String) to get Typeface for the font name}
     *
     * @return list of available font names
     */
    public List<String> getFontNames() {
        return fontNames;
    }

    /**
     * @return Default Font Name - <b>Helvetica</b>
     */
    public String getDefaultFontName() {
        return DEFAULT_FONT_NAME;
    }
}