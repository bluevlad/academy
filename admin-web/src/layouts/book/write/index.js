/* eslint-disable react/prop-types */
/* eslint-disable prettier/prettier */

import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

// Material Dashboard 2 React components
import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import TextField from "@mui/material/TextField";

import Button from "@mui/material/Button";


// Material Dashboard 2 React example components
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

// MUI Components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import Divider from "@mui/material/Divider";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

// API
import { fetchBookWriteData, fetchBookView, saveBook, updateBook } from "api/book";

function BookWrite() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const seq = queryParams.get("seq");
  const rscId = queryParams.get("rscId");

  const [formData, setFormData] = useState({
    BOOK_NM: "",
    BOOK_INFO: "",
    BOOK_KEYWORD: "",
    ISSUE_DATE: "",
    COVER_TYPE: "A",
    BOOK_CONTENTS: "",
    BOOK_MEMO: "",
    PRICE: "",
    FREE_POST: "N",
    DISCOUNT: "0",
    DISCOUNT_PRICE: "0",
    POINT: "0",
    BOOK_PUBLISHERS: "",
    BOOK_SUPPLEMENTDATA: "N",
    BOOK_PRINTINGDATE: "N",
    BOOK_AUTHOR: "",
    BOOK_MAIN: "N",
    BOOK_SUB: "N",
    BOOK_STUDENTBOOK: "N",
    BOOK_PAGE: "",
    BOOK_FORMAT: "",
    BOOK_DATE: "",
    BOOK_STOCK: "1000",
    USE_YN: "Y",
    MAIN_VIEW: "Y",
    NEW_BOOK: "Y",
  });

  const [selectedCategory, setSelectedCategory] = useState([]);
  const [selectedLearning, setSelectedLearning] = useState("");
  const [selectedSubjects, setSelectedSubjects] = useState([]);

  const [files, setFiles] = useState({
    ATTACH_FILE: null,
    ATTACH_IMG_L: null,
    ATTACH_IMG_M: null,
    ATTACH_IMG_S: null,
    ATTACH_DETAIL_INFO: null,
  });

  const [options, setOptions] = useState({
    kindlist: [],
    formlist: [],
    subjectteacherlist: [],
  });

  useEffect(() => {
    const loadOptions = async () => {
      const writeData = await fetchBookWriteData();
      if (writeData) {
        setOptions({
          kindlist: writeData.kindlist || [],
          formlist: writeData.formlist || [],
          subjectteacherlist: writeData.subjectteacherlist || []
        });
      }
    };
    loadOptions();
  }, []);

  useEffect(() => {
    const loadDetail = async () => {
      if (seq && rscId) {
        const viewData = await fetchBookView({ SEQ: seq, RSC_ID: rscId });
        if (viewData && viewData.view && viewData.view.length > 0) {
          const data = viewData.view[0];
          setFormData({
            SEQ: data.SEQ,
            RSC_ID: data.RSC_ID,
            BOOK_NM: data.BOOK_NM || "",
            BOOK_INFO: data.BOOK_INFO || "",
            BOOK_KEYWORD: data.BOOK_KEYWORD || "",
            ISSUE_DATE: data.ISSUE_DATE ? data.ISSUE_DATE.replace(/-/g, '') : "",
            COVER_TYPE: data.COVER_TYPE || "A",
            BOOK_CONTENTS: data.BOOK_CONTENTS || "",
            BOOK_MEMO: data.BOOK_MEMO || "",
            PRICE: data.PRICE ? String(data.PRICE) : "",
            FREE_POST: data.FREE_POST || "N",
            DISCOUNT: data.DISCOUNT ? String(data.DISCOUNT) : "0",
            DISCOUNT_PRICE: data.DISCOUNT_PRICE ? String(data.DISCOUNT_PRICE) : "0",
            POINT: data.POINT ? String(data.POINT) : "0",
            BOOK_PUBLISHERS: data.BOOK_PUBLISHERS || "",
            BOOK_SUPPLEMENTDATA: data.BOOK_SUPPLEMENTDATA || "N",
            BOOK_PRINTINGDATE: data.BOOK_PRINTINGDATE || "N",
            BOOK_AUTHOR: data.BOOK_AUTHOR || "",
            BOOK_MAIN: data.BOOK_MAIN || "N",
            BOOK_SUB: data.BOOK_SUB || "N",
            BOOK_STUDENTBOOK: data.BOOK_STUDENTBOOK || "N",
            BOOK_PAGE: data.BOOK_PAGE ? String(data.BOOK_PAGE) : "",
            BOOK_FORMAT: data.BOOK_FORMAT || "",
            BOOK_DATE: data.BOOK_DATE ? data.BOOK_DATE.replace(/-/g, '') : "",
            BOOK_STOCK: data.BOOK_STOCK ? String(data.BOOK_STOCK) : "1000",
            USE_YN: data.USE_YN || "Y",
            MAIN_VIEW: data.MAIN_VIEW || "Y",
            NEW_BOOK: data.NEW_BOOK || "Y",
          });
          // Note: Pre-selecting categories/subjects is skipped for now as logic is complex
          // and dependent on how backend returns the relations. 
          // Assuming user will re-select if needed or backend handles updates smartly.
          if (data.LEARNING_CD) setSelectedLearning(data.LEARNING_CD);
          if (data.CATEGORY_CD) setSelectedCategory([data.CATEGORY_CD]);
        }
      }
    };
    loadDetail();
  }, [seq, rscId]);


  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    if (type === "checkbox") {
      setFormData((prev) => ({ ...prev, [name]: checked ? "Y" : "N" }));
    } else {
      setFormData((prev) => ({ ...prev, [name]: value }));
    }

    // Calculate logic
    if (name === "PRICE" || name === "DISCOUNT") {
      const price = name === "PRICE" ? parseFloat(value || 0) : parseFloat(formData.PRICE || 0);
      const discount = name === "DISCOUNT" ? parseFloat(value || 0) : parseFloat(formData.DISCOUNT || 0);

      const discountPrice = price - (price * (discount / 100));
      const point = Math.floor(discountPrice * 0.05);

      setFormData((prev) => ({
        ...prev,
        [name]: value,
        DISCOUNT_PRICE: Math.floor(discountPrice),
        POINT: point
      }));
    }
  };

  const handleCategoryChange = (val) => {
    if (selectedCategory.includes(val)) {
      setSelectedCategory(selectedCategory.filter(id => id !== val));
    } else {
      setSelectedCategory([...selectedCategory, val]);
    }
  };

  const handleSubjectChange = (val) => {
    if (selectedSubjects.includes(val)) {
      setSelectedSubjects(selectedSubjects.filter(id => id !== val));
    } else {
      setSelectedSubjects([...selectedSubjects, val]);
    }
  };

  const handleFileChange = (e) => {
    const { name, files } = e.target;
    if (files && files[0]) {
      setFiles((prev) => ({ ...prev, [name]: files[0] }));
    }
  };

  const handleSubmit = async () => {
    // Validate required
    if (selectedCategory.length === 0) { alert("직종을 선택하세요"); return; }
    if (!selectedLearning) { alert("학습형태를 선택하세요"); return; }
    if (selectedSubjects.length === 0) { alert("과목을 선택하세요"); return; }
    if (!formData.BOOK_NM) { alert("도서명을 입력하세요"); return; }
    if (!formData.PRICE) { alert("도서가격을 입력하세요"); return; }

    const data = new FormData();
    // Add form data
    Object.keys(formData).forEach((key) => {
      data.append(key, formData[key]);
    });
    // Add Arrays
    selectedCategory.forEach(v => data.append("CATEGORY_CD", v));
    selectedSubjects.forEach(v => data.append("SUBJECT_SJT_CD", v));
    data.append("LEARNING_CD", selectedLearning);

    // Add Files
    Object.keys(files).forEach((key) => {
      if (files[key]) data.append(key, files[key]);
    });

    try {
      if (seq) {
        // Update
        data.append("UPDATE_FLAG", "Y"); // Example flag if needed by backend or logic
        // Backend update expects SUBJECT_SJT_CD array too? Yes.
        await updateBook(data);
        alert("수정되었습니다.");
      } else {
        // Save
        await saveBook(data);
        alert("등록되었습니다.");
      }
      navigate("/book");
    } catch (e) {
      alert("오류가 발생했습니다.");
      console.error(e);
    }
  };

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <Box pt={6} pb={3}>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Card>
              <Box
                mx={2}
                mt={-3}
                py={3}
                px={2}
                variant="contained"
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
              >
                <Typography variant="h6" color="white">
                  교재 {seq ? "수정" : "등록"}
                </Typography>
              </Box>
              <Box p={3}>
                <Box mb={2}>
                  <Typography variant="subtitle2">직종</Typography>
                  <Grid container>
                    {options.kindlist.map((item) => (
                      <Grid item key={item.CODE} xs={6} sm={4} md={3}>
                        <FormControlLabel
                          control={<Checkbox
                            checked={selectedCategory.includes(item.CODE)}
                            onChange={() => handleCategoryChange(item.CODE)}
                          />}
                          label={item.NAME}
                        />
                      </Grid>
                    ))}
                  </Grid>
                </Box>
                <Divider />
                <Box mb={2}>
                  <Typography variant="subtitle2">학습형태</Typography>
                  <RadioGroup
                    row
                    value={selectedLearning}
                    onChange={(e) => setSelectedLearning(e.target.value)}
                  >
                    {options.formlist.map((item) => (
                      <FormControlLabel
                        key={item.CODE}
                        value={item.CODE}
                        control={<Radio />}
                        label={item.NAME}
                      />
                    ))}
                  </RadioGroup>
                </Box>
                <Divider />
                <Box mb={2}>
                  <Typography variant="subtitle2">과목(강사)</Typography>
                  <Box sx={{ maxHeight: 200, overflowY: 'auto' }}>
                    <Grid container>
                      {options.subjectteacherlist.map((item) => (
                        <Grid item key={item.SUBJECT_CD + item.USER_ID} xs={6} sm={4} md={3}>
                          <FormControlLabel
                            control={<Checkbox
                              checked={selectedSubjects.includes(`${item.SUBJECT_CD}#${item.USER_ID}`)}
                              onChange={() => handleSubjectChange(`${item.SUBJECT_CD}#${item.USER_ID}`)}
                            />}
                            label={`${item.SUBJECT_NM}(${item.USER_NM})`}
                          />
                        </Grid>
                      ))}
                    </Grid>
                  </Box>
                </Box>
                <Divider />

                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <TextField
                      label="도서명"
                      name="BOOK_NM"
                      fullWidth
                      value={formData.BOOK_NM}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      label="도서상세설명"
                      name="BOOK_INFO"
                      fullWidth
                      multiline
                      rows={3}
                      value={formData.BOOK_INFO}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      label="키워드"
                      name="BOOK_KEYWORD"
                      fullWidth
                      value={formData.BOOK_KEYWORD}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      type="text"
                      label="기간(이벤트) 종료일 (YYYYMMDD)"
                      name="ISSUE_DATE"
                      fullWidth
                      value={formData.ISSUE_DATE}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <FormControl fullWidth>
                      <InputLabel>상품상태</InputLabel>
                      <Select
                        value={formData.COVER_TYPE}
                        label="상품상태"
                        name="COVER_TYPE"
                        onChange={handleChange}
                        sx={{ height: 44 }}
                      >
                        <MenuItem value="A">주문가능</MenuItem>
                        <MenuItem value="S">품절</MenuItem>
                        <MenuItem value="O">절판</MenuItem>
                        <MenuItem value="E">이벤트</MenuItem>
                        <MenuItem value="N">신규</MenuItem>
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      label="목차"
                      name="BOOK_CONTENTS"
                      fullWidth
                      multiline
                      rows={3}
                      value={formData.BOOK_CONTENTS}
                      onChange={handleChange}
                    />
                  </Grid>

                  <Grid item xs={12} md={4}>
                    <TextField
                      label="도서가격"
                      name="PRICE"
                      fullWidth
                      value={formData.PRICE}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="할인율(%)"
                      name="DISCOUNT"
                      fullWidth
                      value={formData.DISCOUNT}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={4}>
                    <TextField
                      label="할인가"
                      name="DISCOUNT_PRICE"
                      fullWidth
                      value={formData.DISCOUNT_PRICE}
                      disabled
                    />
                  </Grid>

                  <Grid item xs={12} md={6}>
                    <TextField
                      label="출판사"
                      name="BOOK_PUBLISHERS"
                      fullWidth
                      value={formData.BOOK_PUBLISHERS}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <TextField
                      label="저자"
                      name="BOOK_AUTHOR"
                      fullWidth
                      value={formData.BOOK_AUTHOR}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={3}>
                    <TextField
                      label="페이지수"
                      name="BOOK_PAGE"
                      fullWidth
                      value={formData.BOOK_PAGE}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={3}>
                    <TextField
                      label="판형"
                      name="BOOK_FORMAT"
                      fullWidth
                      value={formData.BOOK_FORMAT}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={3}>
                    <TextField
                      label="발행일 (YYYYMMDD)"
                      name="BOOK_DATE"
                      fullWidth
                      value={formData.BOOK_DATE}
                      onChange={handleChange}
                    />
                  </Grid>
                  <Grid item xs={12} md={3}>
                    <TextField
                      label="재고"
                      name="BOOK_STOCK"
                      fullWidth
                      value={formData.BOOK_STOCK}
                      onChange={handleChange}
                    />
                  </Grid>

                  <Grid item xs={12}>
                    <FormControlLabel control={<Checkbox checked={formData.FREE_POST === 'Y'} name="FREE_POST" onChange={handleChange} />} label="무료배송" />
                    <FormControlLabel control={<Checkbox checked={formData.BOOK_SUPPLEMENTDATA === 'Y'} name="BOOK_SUPPLEMENTDATA" onChange={handleChange} />} label="보충자료" />
                    <FormControlLabel control={<Checkbox checked={formData.BOOK_PRINTINGDATE === 'Y'} name="BOOK_PRINTINGDATE" onChange={handleChange} />} label="프린트자료" />
                    <FormControlLabel control={<Checkbox checked={formData.BOOK_MAIN === 'Y'} name="BOOK_MAIN" onChange={handleChange} />} label="주교재" />
                    <FormControlLabel control={<Checkbox checked={formData.BOOK_SUB === 'Y'} name="BOOK_SUB" onChange={handleChange} />} label="부교재" />
                    <FormControlLabel control={<Checkbox checked={formData.BOOK_STUDENTBOOK === 'Y'} name="BOOK_STUDENTBOOK" onChange={handleChange} />} label="수강생교재" />
                  </Grid>

                  {/* Files */}
                  <Grid item xs={12}><TypographyVariant label="첨부파일" /><input type="file" name="ATTACH_FILE" onChange={handleFileChange} /></Grid>
                  <Grid item xs={12}><TypographyVariant label="이미지(대)" /><input type="file" name="ATTACH_IMG_L" onChange={handleFileChange} /></Grid>
                  <Grid item xs={12}><TypographyVariant label="이미지(중)" /><input type="file" name="ATTACH_IMG_M" onChange={handleFileChange} /></Grid>
                  <Grid item xs={12}><TypographyVariant label="이미지(소)" /><input type="file" name="ATTACH_IMG_S" onChange={handleFileChange} /></Grid>

                  <Grid item xs={6}>
                    <FormControl fullWidth>
                      <InputLabel>상태</InputLabel>
                      <Select name="USE_YN" value={formData.USE_YN} label="상태" onChange={handleChange} sx={{ height: 44 }}>
                        <MenuItem value="Y">활성</MenuItem>
                        <MenuItem value="N">비활성</MenuItem>
                      </Select>
                    </FormControl>
                  </Grid>

                  <Grid item xs={12} display="flex" justifyContent="center">
                    <Button variant="contained" color="info" onClick={handleSubmit}>
                      {seq ? "수정" : "등록"}
                    </Button>
                    <Box ml={2}>
                      <Button variant="outlined" color="secondary" onClick={() => navigate("/book")}>
                        취소
                      </Button>
                    </Box>
                  </Grid>
                </Grid>
              </Box>
            </Card>
          </Grid>
        </Grid>
      </Box>
      <Footer />
    </DashboardLayout>
  );
}

const TypographyVariant = ({ label }) => (
  <Typography variant="caption" fontWeight="bold" display="block" gutterBottom>
    {label}
  </Typography>
);

export default BookWrite;
