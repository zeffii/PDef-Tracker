~subcell_string_color = { |ndx, cellstr|
    switch (ndx,
        4, {Color.fromHexString("FFDDD1")},
        6, {Color(0.78, 0.97, 0.77)},
        7, {

            if (cellstr != "....",
                { Color(0.38, 0.52, 0.98)},
                { Color(0.53, 0.73, 0.93)})
        },
        {Color(0.98, 0.97, 0.97, 1.0)} // default
    )


};

~subcell_color = Color(0.6, 0.8, 0.88, 1.0);

~cell_darker = Color(0.5, 0.8, 0.9, 1.0);
~cell_dark = Color(0.32, 0.82, 0.92, 1.0);
~cell_light = Color(0.84, 0.84, 0.84, 1.0);
~cell_colors = [~cell_darker, ~cell_dark, ~cell_light];


~waveform_bgcol = Color.blue(0.24);
~waveform_wavecol = Color(0.47, 1.0, 0.5);
~waveform_selectioncol = Color(0.2, 0.4, 0.5);
~waveform_timecursorcol = Color(0.9, 0.4, 0.4);
~info_text_color = Color(0.62, 0.87, 0.95, 1.0);