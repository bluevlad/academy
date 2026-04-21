# Update Footer Copyright

## Objective
Modify the Footer component to display the user's copyright information alongside the existing Creative Tim copyright.

## Steps
1.  **Modify `src/examples/Footer/index.js`**:
    *   Add a new section in the footer to display the user's copyright.
    *   Ensure the existing copyright remains compliant.
    *   Use a separator or new line to distinguish the two.
    *   Add default props for the user's company info to make it easily configurable.

## Implementation Details
-   Target File: `src/examples/Footer/index.js`
-   Add `userCompany` prop with `href` and `name`.
-   Render "Developed by {name}" after the existing text.
