s.boot;

(

GUI.qt;
Window.closeAll;

w = Window.new("ptrk", Rect.new(1140, 530, 760, 520))
    .front
    .alwaysOnTop_(true);

u = UserView(w, Rect(60,43, 750, 500))
    .backColor_(Color(0.9, 0.9, 0.9, 0.5));

// pattern variables
~num_cols = 4;
~num_rows = 16;
~subcell_color = Color(0.6, 0.8, 0.88, 1.0);
~cell_x_offset = 4;
~cell_y_offset = 4;

// cell variables
~split = 0.5;
~subcells = [3, ~split, 2,~split, 2, ~split, 6];
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
            Color(0.95, 0.95, 0.95, 1.0).setFill;
            Pen.addRect(Rect(xpos, ypos, ~cell_width, ~cell_height));
            Pen.fill;

            ~subcellx = 0;
            ~subcells.do { |vdx |
                ~text_rect = Rect(~subcellx + xpos, ypos, vdx*~char_width, ~cell_height);
                // Color(0.82, 0.94, 0.99, 1.0).setFill;
                if (vdx > ~split, {
                    ~tv = StaticText(u, ~text_rect);
                    ~tv.string("===");
                    ~tv.font = "Consolas";
                    ~tv.background = Color(0.8, 0.9, 0.9, 0.5);
                    ~tv.stringColor = Color(0.3, 0.3, 0.3, 1.0);

                },{});
                ~subcellx = (~subcellx + (~char_width * vdx));
            };

        };
    };



}

);
