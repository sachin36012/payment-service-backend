INSERT INTO paymentdb.users (email, enabled, name, password, ph_no, pin, role, uuid) VALUES
('alice@example.com', 1, 'Alice Johnson', 'password123', '555-1234', '1234', 'USER', '550e8400-e29b-41d4-a716-446655440000'),
('bob@example.com', 1, 'Bob Smith', 'password123', '555-2345', '2345', 'ADMIN', '550e8400-e29b-41d4-a716-446655440001'),
('carol@example.com', 1, 'Carol White', 'password123', '555-3456', '3456', 'USER', '550e8400-e29b-41d4-a716-446655440002'),
('dave@example.com', 1, 'Dave Brown', 'password123', '555-4567', '4567', 'USER', '550e8400-e29b-41d4-a716-446655440003'),
('eve@example.com', 1, 'Eve Black', 'password123', '555-5678', '5678', 'ADMIN', '550e8400-e29b-41d4-a716-446655440004'),
('frank@example.com', 1, 'Frank Green', 'password123', '555-6789', '6789', 'USER', '550e8400-e29b-41d4-a716-446655440005'),
('grace@example.com', 1, 'Grace Lee', 'password123', '555-7890', '7890', 'USER', '550e8400-e29b-41d4-a716-446655440006'),
('heidi@example.com', 1, 'Heidi King', 'password123', '555-8901', '8901', 'USER', '550e8400-e29b-41d4-a716-446655440007'),
('ivan@example.com', 1, 'Ivan Young', 'password123', '555-9012', '9012', 'ADMIN', '550e8400-e29b-41d4-a716-446655440008'),
('judy@example.com', 1, 'Judy Hall', 'password123', '555-0123', '0123', 'USER', '550e8400-e29b-41d4-a716-446655440009');

-- 1. Insert into cards
INSERT INTO paymentdb.cards (cvv, expiry, last_four_digits, name, number, user_id) VALUES
('123', '12/26', '1234', 'Alice Johnson', '4111111111111234', 1),
('456', '11/25', '5678', 'Bob Smith', '4111111111115678', 2),
('789', '10/24', '9012', 'Carol White', '411111111119012', 3),
('321', '09/27', '3456', 'Dave Brown', '411111111113456', 4),
('654', '08/28', '7890', 'Eve Black', '411111111117890', 5);

-- 2. Insert into payment_gateways
INSERT INTO paymentdb.payment_gateways (details, name) VALUES
('Supports Visa, MasterCard, Amex', 'Stripe'),
('Supports PayPal accounts and cards', 'PayPal'),
('Bank transfer and ACH payments', 'BankTransfer'),
('Mobile payments via Apple Pay and Google Pay', 'MobilePay'),
('Crypto payments (BTC, ETH)', 'CryptoGateway');

-- 3. Insert into subscriptions
INSERT INTO paymentdb.subscriptions (amount, description, name, payment_gateway_id, user_id) VALUES
(19.99, 'Monthly streaming subscription', 'StreamPlus', 1, 1),
(9.99, 'Cloud storage basic plan', 'CloudBox', 2, 2),
(29.99, 'Online course premium access', 'EduPro', 3, 3),
(14.99, 'Music unlimited plan', 'MusicNow', 4, 4),
(49.99, 'Crypto trading platform', 'CryptoElite', 5, 5);

-- 4. Insert into user_subscriptions
INSERT INTO paymentdb.user_subscriptions (end_date, start_date, status, card_id, user_id) VALUES
('2024-12-31 23:59:59.000000', '2024-01-01 00:00:00.000000', 'ACTIVE', 1, 1),
('2024-11-30 23:59:59.000000', '2024-02-01 00:00:00.000000', 'CANCELLED', 2, 2),
('2024-10-31 23:59:59.000000', '2024-03-01 00:00:00.000000', 'ACTIVE', 3, 3),
('2024-09-30 23:59:59.000000', '2024-04-01 00:00:00.000000', 'EXPIRED', 4, 4),
('2024-08-31 23:59:59.000000', '2024-05-01 00:00:00.000000', 'ACTIVE', 5, 5);

-- 5. Insert into payment_histories
INSERT INTO paymentdb.payment_histories (amount, message, status, subscription_id, user_id) VALUES
(19.99, 'Payment processed successfully', 'SUCCESS', 1, 1),
(9.99, 'Payment failed due to insufficient funds', 'FAILED', 2, 2),
(29.99, 'Awaiting payment confirmation', 'WAITING', 3, 3),
(14.99, 'Payment processed successfully', 'SUCCESS', 4, 4),
(49.99, 'Payment failed: card expired', 'FAILED', 5, 5);
