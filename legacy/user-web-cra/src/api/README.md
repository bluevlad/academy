# API Directory

This directory contains the API integration modules for the application.

## Structure

The API code is organized by feature domain. Each folder contains the necessary request logic for interacting with the backend services for that specific domain.

- **admin**: Administrative functions (auth, code, menu).
- **banner**: Banner management.
- **board**: Board and post management.
- **book**: Book management.
- **coop**: Cooperative partner management.
- **counsel**: Counseling schedule management.
- **dday**: D-Day event management.
- **event**: Event management.
- **exam**: Exam and result management.
- **lecture**: Lecture and course management.
- **login**: Authentication (Login, Register, Profile).
- **manage**: Sales and teacher calculation management.
- **member**: Member management.
- **menu**: Menu structure management.
- **note**: Note management.
- **order**: Order and coupon management.
- **popup**: Popup management.
- **stat**: Statistics and reporting.
- **survey**: Survey and question bank management.

## Usage

Import the necessary functions from the respective domain file to make network requests.

Example:
```javascript
import { login } from "api/login";
```
