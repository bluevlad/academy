# Admin API

Provides functions for administrative tasks.

## Files

- `auth.js`
- `code.js`
- `menu.js`

## Functions

### Auth (`auth.js`)

#### `getAuthList(params)`
Retrieves a list of authentication records.

#### `getAuthDetail(siteId)`
Retrieves details of a specific authentication record.

#### `insertAuth(data)`
Creates a new authentication record.

#### `updateAuth(data)`
Updates an existing authentication record.

#### `deleteAuth(siteId)`
Deletes an authentication record.

#### `getOnlineMenuList(siteId)`
Retrieves the online menu list for a site.

### Code (`code.js`)

#### `getCodeList(params)`
Retrieves a list of system codes.

#### `insertCode(data)`
Creates a new system code.

#### `updateCode(data)`
Updates an existing system code.

#### `deleteCode(codeNo)`
Deletes a system code.

### Menu (`menu.js`)

#### `getMenuTree()`
Retrieves the menu tree structure.

#### `insertMenu(data)`
Creates a new menu item.

#### `updateMenu(data)`
Updates an existing menu item.

#### `deleteMenu(menuId)`
Deletes a menu item.

#### `checkMenuId(menuId)`
Checks if a menu ID is valid/available.
