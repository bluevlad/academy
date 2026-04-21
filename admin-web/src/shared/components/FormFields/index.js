/**
 * React Hook Form + MUI 통합 폼 필드 컴포넌트
 *
 * RHF의 Controller와 MUI 컴포넌트를 조합한 재사용 가능한 폼 필드
 *
 * Usage:
 *   import { FormTextField, FormSelect, FormSwitch } from "shared/components/FormFields";
 *
 *   <FormTextField control={control} name="title" label="제목" rules={{ required: "필수" }} />
 *   <FormSelect control={control} name="type" label="타입" options={typeOptions} />
 */
import { Controller } from "react-hook-form";
import TextField from "@mui/material/TextField";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormHelperText from "@mui/material/FormHelperText";
import InputLabel from "@mui/material/InputLabel";
import Switch from "@mui/material/Switch";

/**
 * RHF 텍스트 필드
 * @param {Object} control - useForm의 control
 * @param {string} name - 필드명
 * @param {string} label - 라벨
 * @param {Object} rules - RHF 유효성 검증 규칙
 * @param {Object} rest - MUI TextField에 전달할 추가 props
 */
export function FormTextField({ control, name, label, rules, ...rest }) {
  return (
    <Controller
      name={name}
      control={control}
      rules={rules}
      render={({ field, fieldState: { error } }) => (
        <TextField
          {...field}
          label={label}
          error={!!error}
          helperText={error?.message}
          fullWidth
          size="small"
          {...rest}
        />
      )}
    />
  );
}

/**
 * RHF Select 필드
 * @param {Object} control - useForm의 control
 * @param {string} name - 필드명
 * @param {string} label - 라벨
 * @param {Array} options - [{ value, label }]
 * @param {Object} rules - RHF 유효성 검증 규칙
 * @param {Object} rest - MUI Select에 전달할 추가 props
 */
export function FormSelect({ control, name, label, options = [], rules, ...rest }) {
  return (
    <Controller
      name={name}
      control={control}
      rules={rules}
      render={({ field, fieldState: { error } }) => (
        <FormControl fullWidth size="small" error={!!error}>
          <InputLabel>{label}</InputLabel>
          <Select {...field} label={label} {...rest}>
            {options.map((opt) => (
              <MenuItem key={opt.value} value={opt.value}>
                {opt.label}
              </MenuItem>
            ))}
          </Select>
          {error && <FormHelperText>{error.message}</FormHelperText>}
        </FormControl>
      )}
    />
  );
}

/**
 * RHF Switch (토글)
 * @param {Object} control - useForm의 control
 * @param {string} name - 필드명
 * @param {string} label - 라벨
 * @param {string} activeValue - 활성 값 (기본: "Y")
 * @param {string} inactiveValue - 비활성 값 (기본: "N")
 */
export function FormSwitch({
  control,
  name,
  label,
  activeValue = "Y",
  inactiveValue = "N",
}) {
  return (
    <Controller
      name={name}
      control={control}
      render={({ field }) => (
        <FormControlLabel
          control={
            <Switch
              checked={field.value === activeValue}
              onChange={(e) =>
                field.onChange(e.target.checked ? activeValue : inactiveValue)
              }
            />
          }
          label={label}
        />
      )}
    />
  );
}

/**
 * RHF Textarea (다중행 텍스트)
 * @param {Object} control - useForm의 control
 * @param {string} name - 필드명
 * @param {string} label - 라벨
 * @param {number} rows - 행 수 (기본: 4)
 * @param {Object} rules - RHF 유효성 검증 규칙
 */
export function FormTextArea({ control, name, label, rows = 4, rules, ...rest }) {
  return (
    <Controller
      name={name}
      control={control}
      rules={rules}
      render={({ field, fieldState: { error } }) => (
        <TextField
          {...field}
          label={label}
          multiline
          rows={rows}
          error={!!error}
          helperText={error?.message}
          fullWidth
          size="small"
          {...rest}
        />
      )}
    />
  );
}
