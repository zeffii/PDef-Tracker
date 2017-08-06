

~ptrk_instr_view = { |tview|

    tview.removeAll;
    ~show_sample_paths.postln;

    Color(0.3, 0.7, 0.94).setFill;
    Pen.addRect(Rect(0,180, 480, 200));
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
    ~waveform_view.setSelectionSize(0, 345);
    ~soundfile = ~waveform_view.soundfile;
    ~active_path = PathName.new(~soundfile.path);

    ~left_text = 212;
    ~right_text = 320;
    ~info_vertical_start = 180;
    ~info_vertical_dist = 17;

    ~waveform_view.selections[0].postln;

    // Sample Info Area
    ~info_dur_time = ~soundfile.duration.asStringPrec(3) ++ " sec";
    ~info_dur_frames = ~soundfile.numFrames.asString ++ " frames";
    ~length_info = ~info_dur_time ++ " | " ++ ~info_dur_frames;
    ~info = [
        ["Filename: ", ~active_path.fileName],
        ["Rate: ", ~soundfile.sampleRate],
        ["Length: ", ~length_info],
        ["Format: ", ~soundfile.sampleFormat]
    ];

    ~info.do { |item, idx|
        ~this_y = ~info_vertical_start + (~info_vertical_dist * idx);
        ~temp_item = StaticText(tview, Rect(~left_text, ~this_y, 100, 20));
        ~temp_item.string = item[0];
        ~temp_item.stringColor = ~info_text_color;
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
        ["Vib.Spd ", 0],
        ["Vib.Amp ", 0],
        ["Vib.Rate ", 0]
    ];

    // This is fugly. I failed to do this in a loop.. but don't want to get bogged down in iteration 1. so.whatevs
    // Global Sample state area
    ~default_volume = StaticText(tview, Rect(0, ~info_vertical_start, 50, 20));
    ~default_volume.stringColor = ~info_text_color;
    ~default_volume.string_(~sample_props[0][0]).align_(\right);
    ~numbox1 = NumberBox(tview, Rect(200, ~info_vertical_start+2, 50, 16));
    ~slider1 = Slider(tview, Rect(50, ~info_vertical_start+2, 150, 16)).action_({~numbox1.value_(~slider1.value)});
    ~slider1.knobColor = ~info_text_color;
    ~slider1.backColor = Color(0.3, 0.8, 0.98);
    ~slider1.focusColor = Color(0.3, 0.8, 0.98);
    //~sliderl.value = ~sample_props[0][1];

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 1);
    ~global_volume = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~global_volume.stringColor = ~info_text_color;
    ~global_volume.string_(~sample_props[1][0]).align_(\right);
    ~numbox2 = NumberBox(tview, Rect(200, ~this_y+2, 50, 16));
    ~slider2 = Slider(tview, Rect(50, ~this_y+2, 150, 16)).action_({~numbox2.value_(~slider2.value)});
    ~slider2.knobColor = ~info_text_color;
    ~slider2.backColor = Color(0.3, 0.8, 0.98);
    ~slider2.focusColor = Color(0.3, 0.8, 0.98);

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 2);
    ~def_pan = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~def_pan.stringColor = ~info_text_color;
    ~def_pan.string_(~sample_props[2][0]).align_(\right);
    ~def_checkbox = CheckBox(tview, Rect(50, ~this_y, 20, 20));
    ~numbox3 = NumberBox(tview, Rect(200, ~this_y+2, 50, 16));
    ~slider3 = Slider(tview, Rect(67, ~this_y+2, 133, 16)).action_({~numbox3.value_(~slider3.value)});
    ~slider3.knobColor = ~info_text_color;
    ~slider3.backColor = Color(0.3, 0.8, 0.98);
    ~slider3.focusColor = Color(0.3, 0.8, 0.98);

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 3);
    ~vib_speed = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~vib_speed.stringColor = ~info_text_color;
    ~vib_speed.string_(~sample_props[3][0]).align_(\right);
    ~numbox4 = NumberBox(tview, Rect(200, ~this_y+2, 50, 16));
    ~slider4 = Slider(tview, Rect(50, ~this_y+2, 150, 16)).action_({~numbox4.value_(~slider4.value)});
    ~slider4.knobColor = ~info_text_color;
    ~slider4.backColor = Color(0.3, 0.8, 0.98);
    ~slider4.focusColor = Color(0.3, 0.8, 0.98);

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 4);
    ~vib_depth = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~vib_depth.stringColor = ~info_text_color;
    ~vib_depth.string_(~sample_props[4][0]).align_(\right);
    ~numbox5 = NumberBox(tview, Rect(200, ~this_y+2, 50, 16));
    ~slider5 = Slider(tview, Rect(50, ~this_y+2, 150, 16)).action_({~numbox5.value_(~slider5.value)});
    ~slider5.knobColor = ~info_text_color;
    ~slider5.backColor = Color(0.3, 0.8, 0.98);
    ~slider5.focusColor = Color(0.3, 0.8, 0.98);

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 5);
    ~vib_rate = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~vib_rate.stringColor = ~info_text_color;
    ~vib_rate.string_(~sample_props[5][0]).align_(\right);
    ~numbox6 = NumberBox(tview, Rect(200, ~this_y+2, 50, 16));
    ~slider6 = Slider(tview, Rect(50, ~this_y+2, 150, 16)).action_({~numbox6.value_(~slider6.value)});
    ~slider6.knobColor = ~info_text_color;
    ~slider6.backColor = Color(0.3, 0.8, 0.98);
    ~slider6.focusColor = Color(0.3, 0.8, 0.98);

    ~this_y = ~info_vertical_start + (~info_vertical_dist * 6);
    ~vib_wform = StaticText(tview, Rect(0, ~this_y, 50, 20));
    ~vib_wform.stringColor = ~info_text_color;
    ~vib_wform.string_("Vib.Form ").align_(\right);

    ~wform_list = PopUpMenu(tview, Rect(50, ~this_y+2, 150, 16))
        .items_([ "Sin", "Saw", "Square", "Tri", "Rnd", "Osc" ])
        .background_(Color.red)
        .stringColor_(Color.white)
        .action_({ arg menu;
            [menu.value, menu.item].postln; // .value returns the integer
        });

    ~buffstart = 340;
    ~bufflen = 4000;
    // if (~waveform_view.selection[0], {
    //     ~buffstart = ~waveform_view.selection[0][0];
    //     ~bufflen = ~waveform_view.selection[0][1];
    // });
    ~buff = Buffer.read(s, ~soundfile.path);
    ~this_y = ~info_vertical_start + (~info_vertical_dist * 7);
    ~playbutton = Button(tview, Rect(50, ~this_y+2, 80, 16))
        .states_([["play", Color.black, Color.white]])
        .mouseDownAction_({

            // buffer start / end are not both covered here, only buffer start.
            if (~waveform_view.selections[0][0] > 0, {
                ~buffstart = ~waveform_view.selections[0][0];
                ~bufflen = ~waveform_view.selections[0][1];
            ~buff = Buffer.read(s, ~soundfile.path, startFrame: ~buffstart); //, numFrames: ~bufflen);
            });

            a = {EnvGen.kr(Env.adsr, doneAction:2) * PlayBuf.ar(2, ~buff)}.play;
            })
        .action_({ arg butt, mod;
            a.release(0.3);
            });

    // Loop (pingpong/normal/off)
    // loop begin
    // loop end
    // SussLoop (on/off)
    // SussBeg
    // SussEnd
    // Quality, channels

    ~sample_entries_rect = Rect(480,0,200,320);

    //tview.decorator = FlowLayout(~sample_entries_rect);

    ("showing new idx" ++ ~list_idx.asString).postln;
    ~glist = EZListView.new(tview, ~sample_entries_rect);
    //~glist.setColors(Color.grey, Color.white, Color.grey, Color.white);

    21.do { |idx|
        ~idx_string = idx.asString.padLeft(2, "0");
        if (~show_sample_paths[idx].size > 0,
            ~fname = PathName.new(~show_sample_paths[idx]).fileName;
            {~idx_string = ~idx_string ++ " " ++ ~fname});

        ~glist.addItem(~idx_string, {
            |a|
            ("this is item" + idx.asString + a).postln;

            if (~show_sample_paths[idx].size > 0,{
                ~active_sample_path = ~show_sample_paths[idx];
                ~fs1.openRead(~active_sample_path);
                idx.postln;
                "loading new sound".postln;
                ~list_idx = idx;
                tview.refresh;
            },
            {
                FileDialog({ |path|
                    ~show_sample_paths[idx] = path[0];
                    ~list_idx = idx;
                    ~active_sample_path = path[0];
                    ~fs1.openRead(~active_sample_path);
                    tview.refresh;
                },
                {"no path selected".postln; });
            });

        });
    };

    ~glist.value = ~list_idx;
}

