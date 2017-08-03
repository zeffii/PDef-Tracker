~ptrk_instr_view = { |tview|

    ~waveform_bgcol.setFill;
    Pen.addRect(Rect(252,180, 228, 200));
    Pen.fill;

    //tview.removeAll;

    // Filename
    ~active_path = PathName.new(~active_sample_path);

    ~id_filename = StaticText(tview, Rect(204, 180, 200,20));
    ~id_filename.string = "Filename";
    ~id_filename_val = StaticText(tview, Rect(260, 180, 200,20));
    ~id_filename_val.string = ~active_path.fileName;
    ~id_filename_val.stringColor = Color(0.62, 0.87, 0.95, 1.0);


    // Speed
    // Loop (pingpong/normal/off)
    // loop begin
    // loop end
    // SussLoop (on/off)
    // SussBeg
    // SussEnd
    // Quality, channels
    // Length (Frames)  ~fsl.duration
}