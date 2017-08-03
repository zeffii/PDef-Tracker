~ptrk_instr_view = { |tview|

    tview.removeAll;

    ~waveform_bgcol.setFill;
    Pen.addRect(Rect(312,180, 228, 200));
    Pen.fill;

    ~waveform_view = SoundFileView.new(~instrument_view, Rect(0, 0, 480, 180));
    ~waveform_view.waveColors = [~waveform_wavecol, ~waveform_wavecol];
    ~waveform_view.setSelectionColor(0, ~waveform_selectioncol);
    ~waveform_view.background = ~waveform_bgcol;

    ~waveform_view.soundfile = ~fs1;
    ~waveform_view.read(0, ~fs1.numFrames);
    ~waveform_view.refresh;
    ~waveform_view.timeCursorColor = ~waveform_timecursorcol;
    ~waveform_view.timeCursorOn = true;
    ~waveform_view.gridOn = false;
    ~waveform_view.setSelectionStart(0, 0);
    ~waveform_view.setSelectionSize(0, 12345);
    ~soundfile = ~waveform_view.soundfile;
    ~active_path = PathName.new(~soundfile.path);

    ~left_text = 212;
    ~right_text = 320;
    ~info_vertical_start = 180;
    ~info_vertical_dist = 17;

    // Sample Info Area
    ~info_dur_time = ~soundfile.duration.asStringPrec(3) ++ " sec";
    ~info_dur_frames = ~soundfile.numFrames.asString ++ " frames";
    ~length_info = ~info_dur_time ++ " | " ++ ~info_dur_frames;
    ~info = [
        ["Filename  ", ~active_path.fileName],
        ["Rate  ", ~soundfile.sampleRate],
        ["Length  ", ~length_info],
        ["Format  ", ~soundfile.sampleFormat]
    ];

    ~info.do { |item, idx|
        ~this_y = ~info_vertical_start + (~info_vertical_dist * idx);
        ~temp_item = StaticText(tview, Rect(~left_text, ~this_y, 100, 20));
        ~temp_item.string = item[0];
        ~temp_item.align = \right;
        ~temp_item_val = StaticText(tview, Rect(~right_text, ~this_y, 200, 20));
        ~temp_item_val.string = item[1];
        ~temp_item_val.stringColor = ~info_text_color;
    };

    // divider
    Pen.strokeColor = Color.white; // ~info_text_color;
    Pen.width_(0.4);
    // Pen.lineDash_([0,1]);
        Pen.moveTo(Point( ~right_text, 10 + (~info_vertical_start + (~info_vertical_dist * 4))));
        Pen.lineTo(Point( ~right_text + 150, 10 + (~info_vertical_start + (~info_vertical_dist * 4))));
    Pen.stroke;

    // Global Sample state area
    ~sample_props = [
        ["Def.Vol ", 0.8],
        ["Glbl.Vol ", 1.0],
        ["Def.Pan ", 0.5],
        ["Vib.Spd ", 0]
    ];

    // Global Sample state area
    ~default_volume = StaticText(tview, Rect(0, ~info_vertical_start, 50, 20));
    ~default_volume.string_(~sample_props[0][0]).align_(\right);
    ~numbox1 = NumberBox(tview, Rect(200, ~info_vertical_start+2, 50, 16));
    ~slider1 = Slider(tview, Rect(50, ~info_vertical_start+2, 150, 16)).action_({~numbox1.value_(~slider1.value)});
    ~slider1.knobColor = ~info_text_color;
    ~slider1.backColor = Color(0.3, 0.8, 0.98);
    ~slider1.focusColor = Color(0.3, 0.8, 0.98);

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 1);
    ~global_volume = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~global_volume.string_(~sample_props[1][0]).align_(\right);
    ~numbox2 = NumberBox(tview, Rect(200, ~this_y+2, 50, 16));
    ~slider2 = Slider(tview, Rect(50, ~this_y+2, 150, 16)).action_({~numbox2.value_(~slider2.value)});
    ~slider2.knobColor = ~info_text_color;
    ~slider2.backColor = Color(0.3, 0.8, 0.98);
    ~slider2.focusColor = Color(0.3, 0.8, 0.98);

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 2);
    ~def_pan = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~def_pan.string_(~sample_props[2][0]).align_(\right);
    ~numbox3 = NumberBox(tview, Rect(200, ~this_y+2, 50, 16));
    ~slider3 = Slider(tview, Rect(50, ~this_y+2, 150, 16)).action_({~numbox3.value_(~slider3.value)});
    ~slider3.knobColor = ~info_text_color;
    ~slider3.backColor = Color(0.3, 0.8, 0.98);
    ~slider3.focusColor = Color(0.3, 0.8, 0.98);

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 3);
    ~vib_speed = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~vib_speed.string_(~sample_props[3][0]).align_(\right);
    ~numbox4 = NumberBox(tview, Rect(200, ~this_y+2, 50, 16));
    ~slider4 = Slider(tview, Rect(50, ~this_y+2, 150, 16)).action_({~numbox4.value_(~slider4.value)});
    ~slider4.knobColor = ~info_text_color;
    ~slider4.backColor = Color(0.3, 0.8, 0.98);
    ~slider4.focusColor = Color(0.3, 0.8, 0.98);

    // Loop (pingpong/normal/off)
    // loop begin
    // loop end
    // SussLoop (on/off)
    // SussBeg
    // SussEnd
    // Quality, channels
}