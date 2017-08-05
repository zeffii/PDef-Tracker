s.boot;


(

~include = { | path |
    ~basspath = thisProcess.nowExecutingPath.dirname;
    ~filepath_utils = ~basspath ++ path;
    // ("including: " ++ ~filepath_utils).postln;
    ~filepath_utils.loadPaths;
};

~list_idx = 0;
~show_sample_paths = Array.fill(21, { "" });
~show_sample_paths[0] = "C:\\Users\\zeffi\\Downloads\\samples\\chr_sam_006.wav";
~show_sample_paths[1] = "C:\\Users\\zeffi\\Downloads\\samples\\chr_sam_008.wav";


~active_sample_path = ~show_sample_paths[0];
~fs1 = SoundFile.new;
~fs1.openRead(~active_sample_path);

~include.value("/ptrk_pattern_data.scd");
~include.value("/ptrk_utils.scd");
~include.value("/ptrk_colors.scd");
~include.value("/ptrk_instrumentview.scd");   // ~ptrk_instr_view



// fontage
~ui_font = Font("Consolas", 10);
~patternview_font = Font("Fixedsys", 13);

// pattern variables
~cell_x_offset = 4;
~cell_y_offset = 2;
~p_offset_x = 60;
~p_offset_y = 43;

// cell variables
~split = 0.36;
~subcells = [3, ~split, 2,~split, 2, ~split, 2, 4];
~char_width = 8;
~cell_height = 16;
~cell_width = (~subcells*~char_width).sum;

~total_cell_width = ~cell_width + ~cell_x_offset;
~total_cell_height = ~cell_height + ~cell_y_offset;
~total_rows_height = (~num_rows * ~total_cell_height) - ~cell_y_offset;

~get_caret_position = {
    ~minx_disp = ~cursor_subcell * ~char_width;

    ~xpos_add = case
    {~cursor_subcell <= 2} { ~minx_disp }
    {~cursor_subcell <= 4} { ~minx_disp + (~split*~char_width) }
    {~cursor_subcell <= 6} { ~minx_disp + (2*~split*~char_width)
    }{~minx_disp + (3*~split*~char_width)};

    ~xpos = (~total_cell_width * ~cursor_cell[0]) + ~xpos_add.value(~cursor_subcell);
    ~ypos = ~cursor_cell[1] * ~total_cell_height;
    [~xpos, ~ypos];
};


GUI.qt;
Window.closeAll;

w = Window.new("ptrk", Rect.new(640, 630, 1280, 420))
    .front
    .alwaysOnTop_(true);
w.view.backColor_(Color(0.83, 0.88, 0.9, 1.0));


~pattern_view = UserView(w, Rect(~p_offset_x, ~p_offset_y, ~total_cell_width*~num_cols, 300))
    .backColor_(Color(0.62, 0.87, 0.95, 1.0));

~instrument_view = UserView(w, Rect(580, ~p_offset_y, 680, 320))
    .backColor_(Color(0.62, 0.87, 0.95, 1.0));

~instrument_view.drawFunc = ~ptrk_instr_view;


~caret = UserView(w, Rect(~p_offset_x, ~p_offset_y, (~total_cell_width*~num_cols), ~total_rows_height));


~caret.drawFunc_{ |tview|
    tview.removeAll;
    ~pos = ~get_caret_position.value();
    Color(1.0, 0, 0, 0.3, 0.3).setFill;
    Pen.addRect(Rect(~pos[0], ~pos[1], ~char_width, ~cell_height));
    Pen.fill;
    //["draw caret:", ~pos].postln;

};

~pattern_view.drawFunc_{ |tview|

    // this wipes the view's current children, else it would repeatedly stack??
    // no evidence that .children grows in size..but it does make a noticable difference
    tview.removeAll;

    ~num_rows.do { |idx |
        ~cell_color = ~cell_colors[((idx % 4) < 1).asInteger];
        ~num_cols.do { | jdx |
            var xpos, ypos;
            xpos = jdx * (~cell_x_offset + ~cell_width );
            ypos = idx * (~cell_y_offset + ~cell_height );

            ~subcellx = 0;
            ~subcells.do { |vdx, ndx |
                ~text_rect = Rect.new(~subcellx + xpos, ypos, vdx*~char_width, ~cell_height);

                if (vdx > ~split, {
                    ~tv = StaticText(tview, ~text_rect);

                    ~named_cell = ~subcell_idx_to_name.value(ndx);
                    ~tv.string = ~pattern_matrix[jdx, idx][~named_cell];
                    ~tv.align = \left;

                    // this function call adjusts subcell colors depending on their content
                    ~tv.stringColor = ~subcell_string_color.value(ndx, ~tv.string);

                    ~tv.font = ~patternview_font;
                    ~tv.background = ~cell_color
                });

                ~subcellx = (~subcellx + (~char_width * vdx));
            };
        };
    };
    // tview.children.size.postln;
    "redraw".postln;
};


w.view.keyDownAction = { |view, char, modifiers, unicode, keycode|
    ~cursor_position.value(keycode, modifiers, ~num_cols, ~num_rows);
    ~m2 = ~keyboard_patternview_handler.value(view, ~pattern_matrix, keycode, modifiers);

    if (~m2.notNil,
        {~pattern_view.refresh},
        {/*'not a note'.postln*/}
    );
    ~caret.refresh;
};


)
