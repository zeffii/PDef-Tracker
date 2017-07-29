// pattern data
~num_cols = 4;
~num_rows = 16;
~pattern_matrix = Array2D.new(~num_cols, ~num_rows);

~pattern_data = { | num_cols, num_rows |

    num_rows.do { |jdx|
        num_cols.do { |idx|
            var mcell = Dictionary.with(*[
                \a->1,
                \b->2,
                \c->3
            ]);
            ~pattern_matrix[idx, jdx] = "... .. .. .. ....";
        };
    };


};