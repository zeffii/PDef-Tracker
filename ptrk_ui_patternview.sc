s.boot;

(

~repeat_str = { |str, num_times|
    var empty_str = "";
    num_times.do {
        empty_str = (empty_str ++ str);
    };
    empty_str;
};

GUI.qt;
Window.closeAll;

w = Window.new("ptrk", Rect.new(1140, 530, 760, 520))
    .front
    .alwaysOnTop_(true);

u = UserView(w, Rect(60,43, 750, 500))
    .backColor_(Color(0.88, 0.88, 0.88, 1.0));

~text_font = "Consolas";

// pattern variables
~num_cols = 4;
~num_rows = 16;
~subcell_color = Color(0.6, 0.8, 0.88, 1.0);
~cell_x_offset = 4;
~cell_y_offset = 4;

// cell variables
~split = 0.5;
~subcells = [3, ~split, 2,~split, 2, ~split, 2, 4];
~char_width = 8;
~cell_height = 16;
~cell_width = (~subcells*~char_width).sum;

/*

tab / shift-tab   |  jump to next / previous  cell
left / right      |  jump to next / previous  subcell
up / down         |  updown cell, retaining subcell position.



*/

u.drawFunc_{ |tview|

    ~num_rows.do { |idx |
        ~num_cols.do { | jdx |
            var xpos, ypos;
            xpos = jdx * (~cell_x_offset + ~cell_width );
            ypos = idx * (~cell_y_offset + ~cell_height );

            // background cell
            Color(0.84, 0.84, 0.84, 1.0).setFill;
            Pen.addRect(Rect(xpos, ypos, ~cell_width, ~cell_height));
            Pen.fill;

            ~subcellx = 0;
            ~subcells.do { |vdx, ndx |
                ~text_rect = Rect(~subcellx + xpos, ypos, vdx*~char_width, ~cell_height);
                // Color(0.82, 0.94, 0.99, 1.0).setFill;
                if (vdx > ~split, {
                    ~tv = StaticText(u, ~text_rect);
                    //~tv.string_("C-4");
                    ~tv.string = ~repeat_str.value(".", vdx);
                    ~tv.align = \left;
                    ~tv.stringColor = Color(0.98, 0.97, 0.97, 1.0);
                    if (ndx == 7, {
                        ~tv.stringColor = Color(0.53, 0.73, 0.93, 1.0);
                    });
                    ~tv.font = Font("Fixedsys", 13);
                    ~tv.background = Color(0.82, 0.91, 0.96, 1.0);

                },{});
                ~subcellx = (~subcellx + (~char_width * vdx));
            };

        };
    };



}

);
