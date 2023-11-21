// Định nghĩa interface cho dữ liệu sản phẩm NFT (thay đổi tùy thuộc vào API của bạn)
interface NFTProduct {
    id: number;
    name: string;
    // Thêm các trường khác tùy theo cấu trúc của API
  }
  
  // Hàm để gửi yêu cầu API và lấy danh sách sản phẩm NFT
  async function fetchNFTProducts(): Promise<NFTProduct[]> {
    try {
      const response = await fetch('URL_API_CUA_BAN/products');
      
      if (!response.ok) {
        throw new Error(`Server returned an error: ${response.status} - ${response.statusText}`);
      }
  
      const data: NFTProduct[] = await response.json();
      return data;
    } catch (error) {
      handleFetchError(error);
      return [];
    }
  }
  
  // Hàm để xử lý lỗi từ Fetch API
  function handleFetchError(error: any) {
    console.error('Error fetching data:', error.message || error);
  }
  
  // Sử dụng hàm để lấy danh sách sản phẩm NFT
  fetchNFTProducts()
    .then((products) => {
      console.log('NFT Products:', products);
    })
    .catch((error) => {
      console.error('Error fetching NFT products:', error);
    });
  