s.boot;

// -- execute first
(

~num_tracks = 6;
~num_rows = 16;

~temp_string = "";
~pattern_matrix = Array2D.new(~num_tracks, ~num_rows);
~num_rows.do { |jdx|
    ~num_tracks.do{ |idx|
        ~pattern_matrix[idx, jdx] = "... .. .. ......";
    };
};

~edit_state = false;
~octave_edit = 5;

~textcol = Color(0.7, 0.7, 0.7, 1.0);
~darkrow = Color(0.8, 0.8, 0.8, 1.0);
~lightrow = Color(0.84, 0.84, 0.84, 1.0);
~color_list = [~lightrow, ~darkrow];

~state_on = Color(1.0, 0, 0, 1.0);
~state_off = Color(0.0, 0, 0, 1.0);
~show_states = [~state_on, ~state_off];


~ypos = 43;
~lineheight = 20;
~cell_width = 97;
~local_offsetx = ~cell_width*1.04;
~cursor_cell = [0, 0];
~textfont = "Consolas";


~cursor_highlight = {
    | keycode |
    switch (keycode,
        39, { ~cursor_cell[0] = ~cursor_cell[0] + 1 },    // right
        37, { ~cursor_cell[0] = ~cursor_cell[0] - 1 },    // left
        38, { ~cursor_cell[1] = ~cursor_cell[1] - 1 },    // up
        40, { ~cursor_cell[1] = ~cursor_cell[1] + 1 }     // down
    );
    ~cursor_cell[0] = ~cursor_cell[0] % ~num_tracks;
    ~cursor_cell[1] = ~cursor_cell[1] % ~num_rows;
};

~keycode_to_note = {
    | keycode, octave |
    switch (keycode,
        // upper row of keyjazz keys
        81, {"C-" ++ octave.asString;},  // q
        50, {"C#" ++ octave.asString;},  // 2
        87, {"D-" ++ octave.asString;},  // w
        51, {"D#" ++ octave.asString;},  // 3
        69, {"E-" ++ octave.asString;},  // e
        82, {"F-" ++ octave.asString;},  // r
        53, {"F#" ++ octave.asString;},  // 5
        84, {"G-" ++ octave.asString;},  // t
        54, {"G#" ++ octave.asString;},  // 6
        89, {"A-" ++ octave.asString;},  // y
        55, {"A#" ++ octave.asString;},  // 7
        85, {"B-" ++ octave.asString;},  // u
        73, {"C-" ++ (octave + 1).asString;},  // i
        57, {"D#" ++ (octave + 1).asString;},  // 9
        79, {"D-" ++ (octave + 1).asString;},  // o
        48, {"D#" ++ (octave + 1).asString;},  // 0
        80, {"E-" ++ (octave + 1).asString;}   // p
    )
};

GUI.qt;
Window.closeAll;

w = Window.new("ptrk", Rect.new(1140, 530, 760, 520))
    .front
    .alwaysOnTop_(true);

u = UserView(w, Rect(60,43, 750, 500))
    .backColor_(Color(0.9, 0.9, 0.9, 0.2));


w.drawFunc_{|me|

    var offsetx = 90;

    // draw header numbers
    ~num_tracks.do { |idx|
        var header_x = 95;
        var text_ypos = 22;

        // channel text
        t = StaticText.new(
            w,
            Rect(header_x + (~local_offsetx*idx), text_ypos, 58, 20)
            ).string_(idx.asString).align_(\left);
        t.font = Font(~textfont, 11);
        t.stringColor_(~textcol);
    };

    // draw row numbers
    ~num_rows.do { |jdx|
        t = StaticText.new(
            w,
            Rect(-4, ~ypos + (~lineheight*jdx), 58, 20)
            ).string_(jdx.asString).align_(\right);
        t.font = Font(~textfont, 13);
        t.stringColor_(~textcol);
    };

    // draw rows
    ~num_rows.do { |jdx|
        var this_idx = 0;

        this_idx = ((jdx % 4) < 1).asInteger;
        ~fillColorCell = ~color_list[this_idx];
        ~fillColorCell.setFill;

        // draw rects
        ~num_tracks.do { |idx|
            ~tb_rect = Rect(60 + (~local_offsetx*idx), 43 + (~lineheight*jdx), ~cell_width, 18);
            Pen.addRect(~tb_rect);
            Pen.fill;

            ~atx = StaticText(w, ~tb_rect);
            ~atx.string = ~pattern_matrix[idx, jdx];
            ~atx.font = "Consolas";
            ~atx.background = Color(0, 0, 0, 0.0);
            ~atx.stringColor = Color(0.3, 0.6, 0.7, 1.0);
        };

    };

};


u.drawFunc_{|me|


    // draw highlight cell
    Color(0.6, 0.8, 0.88, 1.0).setFill;
    // Color(1, 1, 1, 1.0).setStroke;
    Pen.addRect(
        Rect(
        ~local_offsetx*~cursor_cell[0],
        ~lineheight*~cursor_cell[1],
        ~cell_width,
        18)
    );
    // Pen.stroke;
    Pen.fill;

    ~show_states[~edit_state.asInteger].setFill;
    Pen.addOval(Rect(0, 0, 4, 4));
    Pen.fill;

};



u.keyDownAction = {
    | view, char, modifiers, unicode, keycode |
    ~cursor_highlight.value(keycode);

    // keycode.postln;
    switch (keycode,
        32, { ~edit_state = ~edit_state.not;}
    );

    if (~edit_state, {
        ~temp_string = ~temp_string + char;
        ~tfnote = ~keycode_to_note.value(keycode, ~octave_edit);
        // ~pattern_matrix[~cursor_cell[0],~cursor_cell[1]] = ~tfnote;
        // w.refresh;
    },{
        ~temp_string = "";
    });

    // ~temp_string.postln;
    u.refresh;
};

)


w.close;

