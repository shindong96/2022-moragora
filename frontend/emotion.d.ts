import '@emotion/react';
import { SerializedStyles } from '@emotion/react';

type ScreenSize = 'xs' | 'sm' | 'md' | 'lg' | 'xl';
type MediaQuery<T extends string> = Record<
  T,
  (arg0: string[]) => SerializedStyles
>;
type Colors =
  | 'normal'
  | 'subtle-dark'
  | 'subtle-light'
  | 'background'
  | 'surface'
  | 'primary'
  | 'primary-subtle'
  | 'black'
  | 'white'
  | 'tint'
  | 'transparent';

declare module '@emotion/react' {
  export interface Theme {
    colors: Record<Colors, string>;
    media: MediaQuery<ScreenSize>;
  }
}
