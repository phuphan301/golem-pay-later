// Kiểm tra xem có trình duyệt hỗ trợ Web3 không
if (typeof web3 !== 'undefined') {
    // Sử dụng trình duyệt đã cài đặt Web3
    web3 = new Web3(web3.currentProvider);
} else {
    // Nếu không, kết nối đến một nút Ethereum thông qua Infura
    web3 = new Web3(new Web3.providers.HttpProvider("https://mainnet.infura.io/v3/YOUR_INFURA_API_KEY"));
}

// Kết nối với ví Phantom
const phantomProvider = window.solana && window.solana.isPhantom
    ? window.solana
    : null;

if (phantomProvider) {
    // Xác thực và ký giao dịch
    phantomProvider.connect()
        .then(() => {
            // Lấy địa chỉ ví hiện tại
            const phantomAddress = phantomProvider.publicKey.toString();

            // Tạo một giao dịch ví Phantom
            const transaction = {
                to: '0xRecipientAddress', // Địa chỉ nhận thanh toán
                value: web3.utils.toWei('0.1', 'ether'), // Số lượng thanh toán
            };

            // Ký và xác nhận giao dịch
            phantomProvider.signTransaction(transaction)
                .then(signedTransaction => {
                    // Gửi giao dịch đã ký đến mạng Ethereum
                    web3.eth.sendSignedTransaction(signedTransaction.rawTransaction)
                        .on('transactionHash', hash => {
                            console.log('Transaction hash:', hash);
                        })
                        .on('receipt', receipt => {
                            console.log('Transaction receipt:', receipt);
                        })
                        .on('error', error => {
                            console.error('Transaction error:', error);
                        });
                })
                .catch(error => {
                    console.error('Error signing transaction:', error);
                });
        })
        .catch(error => {
            console.error('Error connecting to Phantom:', error);
        });
} else {
    console.error('Phantom wallet not detected.');
}