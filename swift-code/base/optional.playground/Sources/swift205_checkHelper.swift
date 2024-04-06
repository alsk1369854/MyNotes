import Foundation

public struct 日期: Codable {
    public let year: Int
    public let month: Int
    public let day: Int
    
    public static let today = 日期(year: 2022, month: 5, day: 15)
}
