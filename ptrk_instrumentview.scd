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

    ~info = [
        ["Filename  ", ~active_path.fileName],
        ["Rate  ", ~soundfile.sampleRate],
        ["Length  ", ~soundfile.duration.asStringPrec(3) ++ " (seconds)"],
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


    // Loop (pingpong/normal/off)
    // loop begin
    // loop end
    // SussLoop (on/off)
    // SussBeg
    // SussEnd
    // Quality, channels
}