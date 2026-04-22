# Order API

Provides functions for managing coupons and product orders.

## Files

- `coupon.js`
- `freeOrder.js`
- `productOrder.js`

## Functions

### Coupon (`coupon.js`)

#### `getCouponList(params)`
Retrieves a list of coupons.

#### `getCouponView(params)`
Retrieves details of a specific coupon.

#### `getCouponWriteData(params)`
Retrieves data for creating/editing a coupon.

#### `insertCoupon(data)`
Creates a new coupon.

#### `updateCoupon(data)`
Updates an existing coupon.

#### `getCouponUserList(params)`
Retrieves a list of users holding a coupon.

#### `getCoopLectureList(params)`
Retrieves a list of cooperation lectures.

#### `getCoopCouponList(params)`
Retrieves a list of cooperation coupons.

#### `insertCoopCoupon(data)`
Creates a new cooperation coupon.

#### `deleteCoopCoupon(data)`
Deletes a cooperation coupon.

#### `getCouponOrderList(params)`
Retrieves a list of coupon orders.

### Free Order (`freeOrder.js`)

#### `getMemberFreeOrderList(params)`
Retrieves a list of free orders for a member.

#### `getLectureMstInfo(params)`
Retrieves master information for a lecture.

#### `getCategoryList(params)`
Retrieves a list of categories.

#### `getLearningFormList(params)`
Retrieves a list of learning forms.

#### `getSubjectList(params)`
Retrieves a list of subjects.

#### `getLectureListForFreeOrder(params)`
Retrieves a list of lectures for free orders.

#### `insertFreeOrder(data)`
Creates a new free order.

#### `insertFreeOrderMultiple(data)`
Creates multiple free orders.

#### `getChangeLectureList(params)`
Retrieves a list of lectures available for change.

#### `getChangeViewDetail(params)`
Retrieves details for a lecture change.

#### `updateChangeLecture(data)`
Updates a lecture change.

### Product Order (`productOrder.js`)

#### `getProductOrderList(params)`
Retrieves a list of product orders.

#### `getZeroProductOrderList(params)`
Retrieves a list of zero-price product orders.

#### `getFreeLecProductOrderList(params)`
Retrieves a list of free lecture orders.

#### `getOffProductOrderList(params)`
Retrieves a list of offline product orders.

#### `getProductOrderView(params)`
Retrieves details of a specific product order.

#### `getStatusCodeList(params)`
Retrieves a list of status codes.

#### `getPaymentList(params)`
Retrieves a list of payments.

#### `updatePayKind(data)`
Updates the payment method.

#### `updateDepositStatus(data)`
Updates the deposit status.

#### `updateStudyPer(data)`
Updates the study period.

#### `insertOffOrder(data)`
Creates a new offline order.

#### `updateOffOrder(data)`
Updates an existing offline order.

#### `deleteOffOrder(data)`
Deletes an offline order.

#### `insertRefund(data)`
Processing a refund.

#### `deleteRefund(data)`
Cancels a refund.

#### `getMemberView(params)`
Retrieves member details.

#### `getPointHistory(params)`
Retrieves point history.

#### `getMemberCouponList(params)`
Retrieves a list of member coupons.

#### `getMemberClassList(params)`
Retrieves a list of member classes.

#### `getMemberOffClassList(params)`
Retrieves a list of member offline classes.
