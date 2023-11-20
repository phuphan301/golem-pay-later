import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';

// Định nghĩa interface cho sản phẩm
interface Product {
  id: number;
  name: string;
  price: number;
}

// Định nghĩa interface cho phản hồi từ API
interface ApiResponse<T> {
  success: boolean;
  data: T;
  error?: string;
}

// Định nghĩa class quản lý kết nối API
class ApiService {
  private apiUrl: string;

  constructor(apiUrl: string) {
    this.apiUrl = apiUrl;
  }

  // Hàm thực hiện yêu cầu GET
  async get<T>(endpoint: string, config?: AxiosRequestConfig): Promise<ApiResponse<T>> {
    try {
      const response: AxiosResponse<T> = await axios.get(`${this.apiUrl}/${endpoint}`, config);
      return { success: true, data: response.data };
    } catch (error) {
      return { success: false, data: null, error: error.message };
    }
  }

  // Hàm thực hiện yêu cầu POST
  async post<T>(endpoint: string, data?: any, config?: AxiosRequestConfig): Promise<ApiResponse<T>> {
    try {
      const response: AxiosResponse<T> = await axios.post(`${this.apiUrl}/${endpoint}`, data, config);
      return { success: true, data: response.data };
    } catch (error) {
      return { success: false, data: null, error: error.message };
    }
  }

  // Các hàm khác như put, delete có thể được thêm vào tùy ý
}

// Sử dụng API Service
const api = new ApiService('https://api.example.com');

// Lấy danh sách sản phẩm
api.get<Product[]>('products')
  .then((response) => {
    if (response.success) {
      console.log('Product List:', response.data);
    } else {
      console.error('Error:', response.error);
    }
  })
  .catch((error) => {
    console.error('Request failed:', error);
  });

// Gửi yêu cầu tạo mới sản phẩm
const newProduct = {
  name: 'New Product',
  price: 99.99,
};

api.post<Product>('products', newProduct)
  .then((response) => {
    if (response.success) {
      console.log('New Product:', response.data);
    } else {
      console.error('Error:', response.error);
    }
  })
  .catch((error) => {
    console.error('Request failed:', error);
  });
