import { FormControl } from "@angular/forms";

export type MappingFormControls<T> = {
  // [K in keyof T]: [T[K], Function];
  [K in keyof T]: FormControl<T[K] | null>;
};
