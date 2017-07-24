s.boot;

// -- execute first
(

~textcol = Color(0.7, 0.7, 0.7, 1.0);
~darkrow = Color(0.8, 0.8, 0.8, 1.0);
~lightrow = Color(0.84, 0.84, 0.84, 1.0);
~color_list = [~lightrow, ~darkrow];

~num_tracks = 6;
~num_rows = 16;

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
            ~atx.string = "... .. .. ......";
            ~atx.font = "Consolas";
            ~atx.background = Color(0, 0, 0, 0.0);
            ~atx.stringColor = Color(0.3, 0.6, 0.7, 1.0);

        };

    };

};


u.drawFunc_{|me|


    // draw highlight cell
    Color(0.6, 0.8, 0.88, 1.0).setFill;
    Pen.addRect(
         Rect((~local_offsetx*~cursor_cell[0]),
    (~lineheight*~cursor_cell[1]), ~cell_width, 18));
    Pen.fill;

};



u.keyDownAction = {
    arg view, char, modifiers, unicode, keycode;
    // [keycode].postln; //, modifiers, unicode].postln;
    ~cursor_highlight.value(keycode);
    u.refresh;
};

)


w.close;

