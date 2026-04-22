# Manage API

Provides functions for various management tasks including sales and teacher calculations.

## Files

- `categorySale.js`
- `lectureYear.js`
- `teacherCalculate.js`

## Functions

### Category Sale (`categorySale.js`)

#### `getCategorySaleList(params)`
Retrieves a list of category sales.

#### `getCategorySaleDetail(params)`
Retrieves details of a category sale.

#### `getCategorySaleStats(params)`
Retrieves category sale statistics.

#### `getDailySaleList(params)`
Retrieves a list of daily sales.

#### `getMonthlySaleList(params)`
Retrieves a list of monthly sales.

#### `getYearlySaleList(params)`
Retrieves a list of yearly sales.

#### `getCategoryList(params)`
Retrieves a list of categories.

#### `getCategorySaleRateList(params)`
Retrieves a list of category sale rates.

#### `insertCategorySale(data)`
Creates a new category sale record.

#### `updateCategorySale(data)`
Updates an existing category sale record.

#### `deleteCategorySale(data)`
Deletes a category sale record.

### Lecture Year (`lectureYear.js`)

#### `getLectureYearList(params)`
Retrieves a list of lecture years.

#### `getLectureYearDetail(params)`
Retrieves details of a specific lecture year.

#### `getYearList(params)`
Retrieves a list of years.

#### `getLectureYearStats(params)`
Retrieves lecture year statistics.

#### `getCategoryYearStatsList(params)`
Retrieves category year statistics list.

#### `getTeacherYearStatsList(params)`
Retrieves teacher year statistics list.

#### `getYearlySaleStatsList(params)`
Retrieves yearly sale statistics list.

#### `insertLectureYear(data)`
Creates a new lecture year record.

#### `updateLectureYear(data)`
Updates an existing lecture year record.

#### `deleteLectureYear(data)`
Deletes a lecture year record.

#### `insertLectureYearMapping(data)`
Maps a lecture to a year.

#### `deleteLectureYearMapping(data)`
Deletes a lecture-year mapping.

### Teacher Calculate (`teacherCalculate.js`)

#### `getTeacherCalculateList(params)`
Retrieves a list of teacher calculations.

#### `getTeacherCalculateDetail(params)`
Retrieves details of a teacher calculation.

#### `getTeacherCalculateSummaryList(params)`
Retrieves a summary list of teacher calculations.

#### `getMonthlyCalculateList(params)`
Retrieves a list of monthly calculations.

#### `getTeacherLectureSaleList(params)`
Retrieves a list of teacher lecture sales.

#### `getCalculateStats(params)`
Retrieves calculation statistics.

#### `getTeacherList(params)`
Retrieves a list of teachers.

#### `insertTeacherCalculate(data)`
Creates a new teacher calculation.

#### `updateTeacherCalculate(data)`
Updates an existing teacher calculation.

#### `deleteTeacherCalculate(data)`
Deletes a teacher calculation.

#### `updateCalculateStatus(data)`
Updates the status of a calculation.

#### `updatePayment(data)`
Updates payment information.
