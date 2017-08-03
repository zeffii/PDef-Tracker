~ptrk_instr_view = { |tview|

    tview.removeAll;

    ~waveform_bgcol.setFill;
    Pen.addRect(Rect(252,180, 228, 200));
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

    ~active_path = PathName.new(~waveform_view.soundfile.path);

    // Filename
    ~id_filename = StaticText(tview, Rect(152, 180, 100,20));
    ~id_filename.string = "Filename  ";
    ~id_filename.align = \right;
    // ~id_filename.background = Color.grey;
    ~id_filename_val = StaticText(tview, Rect(260, 180, 200,20));
    ~id_filename_val.string = ~active_path.fileName;
    ~id_filename_val.stringColor = Color(0.62, 0.87, 0.95, 1.0);

    // Speed

    ~waveform_view.soundfile.sampleRate

    // Loop (pingpong/normal/off)
    // loop begin
    // loop end
    // SussLoop (on/off)
    // SussBeg
    // SussEnd
    // Quality, channels
    // Length (Frames)  ~fsl.duration
}