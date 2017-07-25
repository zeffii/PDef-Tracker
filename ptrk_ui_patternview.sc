s.boot;

(

~repeat_str = { |str, num_times|
    var empty_str = "";
    num_times.do {
        empty_str = (empty_str ++ str);
    };
    empty_str;
};

~text_font = "Consolas";

~cell_darker = Color(0.5, 0.8, 0.9, 1.0);
~cell_dark = Color(0.32, 0.82, 0.92, 1.0);
~cell_light = Color(0.84, 0.84, 0.84, 1.0);
~cell_colors = [~cell_darker, ~cell_dark, ~cell_light];

// pattern variables
~num_cols = 4;
~num_rows = 16;
~subcell_color = Color(0.6, 0.8, 0.88, 1.0);
~cell_x_offset = 4;
~cell_y_offset = 2;

// cell variables
~split = 0.36;
~subcells = [3, ~split, 2,~split, 2, ~split, 2, 4];
~char_width = 8;
~cell_height = 16;
~cell_width = (~subcells*~char_width).sum;

/*

tab / shift-tab   |  jump to next / previous  cell
left / right      |  jump to next / previous  subcell
up / down         |  updown cell, retaining subcell position.

*/


GUI.qt;
Window.closeAll;

w = Window.new("ptrk", Rect.new(1140, 530, 760, 520))
    .front
    .alwaysOnTop_(true);

u = UserView(w, Rect(60,43, 750, 500))
    .backColor_(Color(0.72, 0.82, 0.89, 1.0));


u.drawFunc_{ |tview|

    ~num_rows.do { |idx |
        ~cell_color = ~cell_colors[((idx % 4) < 1).asInteger];
        ~num_cols.do { | jdx |
            var xpos, ypos;
            xpos = jdx * (~cell_x_offset + ~cell_width );
            ypos = idx * (~cell_y_offset + ~cell_height );

            // background cell
            ~cell_colors[2].setFill;
            // Pen.addRect(Rect(xpos, ypos, ~cell_width, ~cell_height));
            // Pen.fill;

            ~subcellx = 0;
            ~subcells.do { |vdx, ndx |
                ~text_rect = Rect(~subcellx + xpos, ypos, vdx*~char_width, ~cell_height);

                if (vdx > ~split, {
                    ~tv = StaticText(u, ~text_rect);
                    ~tv.string = ~repeat_str.value(".", vdx);
                    ~tv.align = \left;
                    ~tv.stringColor = Color(0.98, 0.97, 0.97, 1.0);
                    if (ndx == 7, {
                        ~tv.stringColor = Color(0.53, 0.73, 0.93, 1.0);
                    });
                    ~tv.font = Font("Fixedsys", 13);
                    ~tv.background = ~cell_color

                },{});
                ~subcellx = (~subcellx + (~char_width * vdx));
            };

        };
    };



}

);
