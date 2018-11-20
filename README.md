# Airdrop

A national park of lions is represented as a 2D array. Each cell of the array represents a region and the cell's value indicates the number of lions there. A region (of lions) may be connected to another vertically or horizontally, but not diagonally.

For each set of *connected* regions, an airdrop equal to the number of total lions in said connected region must be sent. For connected regions, the airdrop goes to the region with the smallest *row + column* value in the set. If two regions share the smallest value, then take the one with the smaller *row* value between the two.

The resulting output is sorted by the *row + column* value of each drop. The algorithm runs in O(*m*\**n*) for *m* columns and *n* rows.
